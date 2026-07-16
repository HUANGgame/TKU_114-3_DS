import java.util.Scanner;

public class ProductManagementSystem {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("test")) {
            runTestCases();
            return;
        }

        Scanner sc = new Scanner(System.in);
        Product[] products = createInitialProducts();
        int addCount = 0;
        int sellCount = 0;
        int restockCount = 0;
        int updatePriceCount = 0;
        int option = -1;

        while (option != 0) {
            printMenu();
            option = readInt(sc, "請輸入選項：");

            switch (option) {
                case 1:
                    displayAllProducts(products);
                    break;
                case 2:
                    searchProduct(products, sc);
                    break;
                case 3:
                    if (addProduct(products, sc)) {
                        addCount++;
                    }
                    break;
                case 4:
                    if (sellProduct(products, sc)) {
                        sellCount++;
                    }
                    break;
                case 5:
                    if (restockProduct(products, sc)) {
                        restockCount++;
                    }
                    break;
                case 6:
                    if (updatePrice(products, sc)) {
                        updatePriceCount++;
                    }
                    break;
                case 7:
                    displayLowStockProducts(products);
                    break;
                case 8:
                    System.out.println("全部庫存總價值：" + calculateTotalValue(products));
                    break;
                case 0:
                    printSummary(addCount, sellCount, restockCount, updatePriceCount);
                    break;
                default:
                    System.out.println("選項錯誤");
                    break;
            }
        }

        sc.close();
    }

    public static Product[] createInitialProducts() {
        Product[] products = new Product[10];
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 150, 30);
        products[4] = new Product("Webcam", 1800, 8);
        return products;
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== 商品管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
    }

    public static void displayAllProducts(Product[] products) {
        boolean hasProduct = false;
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
                hasProduct = true;
            }
        }

        if (!hasProduct) {
            System.out.println("目前沒有商品");
        }
    }

    public static void searchProduct(Product[] products, Scanner sc) {
        String name = readLine(sc, "請輸入商品完整名稱：");
        Product product = findProduct(products, name);

        if (product == null) {
            System.out.println("找不到商品");
        } else {
            System.out.println("搜尋結果：" + product);
        }
    }

    public static Product findProduct(Product[] products, String name) {
        if (name == null) {
            return null;
        }

        String target = name.trim();
        for (Product product : products) {
            if (product != null
                    && product.getName().equalsIgnoreCase(target)) {
                return product;
            }
        }
        return null;
    }

    public static int findEmptyIndex(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean addProduct(Product[] products, Scanner sc) {
        int index = findEmptyIndex(products);
        if (index == -1) {
            System.out.println("商品空間已滿");
            return false;
        }

        String name = readLine(sc, "請輸入商品名稱：");
        if (findProduct(products, name) != null) {
            System.out.println("商品名稱重複");
            return false;
        }

        int price = readInt(sc, "請輸入價格：");
        int stock = readInt(sc, "請輸入庫存：");
        products[index] = new Product(name, price, stock);
        System.out.println("新增成功：" + products[index]);
        return true;
    }

    public static boolean sellProduct(Product[] products, Scanner sc) {
        Product product = findProduct(products, readLine(sc, "請輸入商品名稱："));
        if (product == null) {
            System.out.println("找不到商品");
            return false;
        }

        int quantity = readInt(sc, "請輸入出售數量：");
        if (product.sell(quantity)) {
            System.out.println("出售成功：" + product);
            return true;
        }

        System.out.println("出售失敗");
        return false;
    }

    public static boolean restockProduct(Product[] products, Scanner sc) {
        Product product = findProduct(products, readLine(sc, "請輸入商品名稱："));
        if (product == null) {
            System.out.println("找不到商品");
            return false;
        }

        int quantity = readInt(sc, "請輸入補充數量：");
        if (product.restock(quantity)) {
            System.out.println("補充成功：" + product);
            return true;
        }

        System.out.println("補充失敗");
        return false;
    }

    public static boolean updatePrice(Product[] products, Scanner sc) {
        Product product = findProduct(products, readLine(sc, "請輸入商品名稱："));
        if (product == null) {
            System.out.println("找不到商品");
            return false;
        }

        int price = readInt(sc, "請輸入新價格：");
        if (product.setPrice(price)) {
            System.out.println("修改成功：" + product);
            return true;
        }

        System.out.println("修改失敗");
        return false;
    }

    public static void displayLowStockProducts(Product[] products) {
        boolean hasLowStock = false;
        for (Product product : products) {
            if (product != null && product.isLowStock()) {
                System.out.println(product);
                hasLowStock = true;
            }
        }

        if (!hasLowStock) {
            System.out.println("沒有低庫存商品");
        }
    }

    public static long calculateTotalValue(Product[] products) {
        long total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getInventoryValue();
            }
        }
        return total;
    }

    public static int countProducts(Product[] products) {
        int count = 0;
        for (Product product : products) {
            if (product != null) {
                count++;
            }
        }
        return count;
    }

    public static void printSummary(
            int addCount,
            int sellCount,
            int restockCount,
            int updatePriceCount) {
        System.out.println("=== 操作摘要 ===");
        System.out.println("新增成功次數：" + addCount);
        System.out.println("出售成功次數：" + sellCount);
        System.out.println("補充成功次數：" + restockCount);
        System.out.println("改價成功次數：" + updatePriceCount);
    }

    public static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("請輸入整數：");
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static void runTestCases() {
        int passed = 0;
        int failed = 0;
        Product[] products = createInitialProducts();

        if (countProducts(products) == 5) {
            passed++;
        } else {
            failed++;
        }

        if (findEmptyIndex(products) == 5) {
            passed++;
        } else {
            failed++;
        }

        if (findProduct(products, " mouse ") == products[1]) {
            passed++;
        } else {
            failed++;
        }

        if (findProduct(products, "unknown") == null) {
            passed++;
        } else {
            failed++;
        }

        if (findProduct(products, null) == null) {
            passed++;
        } else {
            failed++;
        }

        if (calculateTotalValue(products) == 65380) {
            passed++;
        } else {
            failed++;
        }

        if (products[2].sell(2) && products[2].getStock() == 3) {
            passed++;
        } else {
            failed++;
        }

        if (!products[2].sell(99) && products[2].getStock() == 3) {
            passed++;
        } else {
            failed++;
        }

        if (products[4].isLowStock()) {
            passed++;
        } else {
            failed++;
        }

        products[5] = new Product("Speaker", 1200, 4);
        if (findProduct(products, "speaker") == products[5]) {
            passed++;
        } else {
            failed++;
        }

        if (products[5].setPrice(1300)
                && products[5].getPrice() == 1300) {
            passed++;
        } else {
            failed++;
        }

        if (!products[5].setPrice(0)
                && products[5].getPrice() == 1300) {
            passed++;
        } else {
            failed++;
        }

        System.out.println("測試通過：" + passed);
        System.out.println("測試失敗：" + failed);
    }
}
