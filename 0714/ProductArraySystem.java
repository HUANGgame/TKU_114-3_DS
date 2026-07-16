import java.util.Scanner;

public class ProductArraySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {
                "Keyboard",
                "Mouse",
                "Monitor",
                "USB Cable",
                "Headset"
        };

        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int option = -1;
        int purchaseCount = 0;
        int restockCount = 0;
        int totalPurchasedItems = 0;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printAllProducts(names, prices, stocks);
                    break;

                case 2:
                    int searchNumber = readProductNumber(sc, names.length);
                    printProduct(searchNumber - 1, names, prices, stocks);
                    break;

                case 3:
                    int buyNumber = readProductNumber(sc, names.length);
                    int buyIndex = buyNumber - 1;

                    System.out.print("請輸入購買數量：");
                    int buyQuantity = sc.nextInt();

                    while (buyQuantity <= 0) {
                        System.out.print("購買數量必須大於 0，請重新輸入：");
                        buyQuantity = sc.nextInt();
                    }

                    if (buyQuantity > stocks[buyIndex]) {
                        System.out.println("庫存不足");
                    } else {
                        stocks[buyIndex] -= buyQuantity;
                        purchaseCount++;
                        totalPurchasedItems += buyQuantity;

                        System.out.println("購買成功");
                        System.out.println("商品：" + names[buyIndex]);
                        System.out.println("購買數量：" + buyQuantity);
                        System.out.println("剩餘庫存：" + stocks[buyIndex]);
                    }
                    break;

                case 4:
                    int restockNumber = readProductNumber(sc, names.length);
                    int restockIndex = restockNumber - 1;

                    int restockQuantity = readPositiveQuantity(
                            sc, "請輸入補貨數量：");

                    stocks[restockIndex] += restockQuantity;
                    restockCount++;

                    System.out.println("補貨成功");
                    System.out.println("商品：" + names[restockIndex]);
                    System.out.println("目前庫存：" + stocks[restockIndex]);
                    break;

                case 5:
                    printLowStockProducts(names, stocks);
                    break;

                case 6:
                    int totalValue = calculateTotalStockValue(prices, stocks);
                    System.out.println("全部庫存總價值：" + totalValue);
                    break;

                case 0:
                    printSummary(
                            purchaseCount,
                            restockCount,
                            totalPurchasedItems,
                            prices,
                            stocks
                    );
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Product Menu ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 查詢商品");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("0. 結束");
    }

    public static void printAllProducts(
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println("=== Product List ===");

        for (int i = 0; i < names.length; i++) {
            System.out.println(
                    (i + 1) + ". " + names[i]
                            + " | Price: " + prices[i]
                            + " | Stock: " + stocks[i]
            );
        }
    }

    public static int readProductNumber(Scanner sc, int productCount) {
        System.out.print("請輸入商品編號：");
        int number = sc.nextInt();

        while (number < 1 || number > productCount) {
            System.out.print("商品編號錯誤，請重新輸入：");
            number = sc.nextInt();
        }

        return number;
    }

    public static void printProduct(
            int index,
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println("商品名稱：" + names[index]);
        System.out.println("商品價格：" + prices[index]);
        System.out.println("商品庫存：" + stocks[index]);
    }

    public static int readPositiveQuantity(Scanner sc, String message) {
        System.out.print(message);
        int quantity = sc.nextInt();

        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            quantity = sc.nextInt();
        }

        return quantity;
    }

    public static void printLowStockProducts(
            String[] names,
            int[] stocks) {

        System.out.println("=== Low Stock Products ===");
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(
                        names[i] + " | Stock: " + stocks[i]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有低庫存商品");
        }
    }

    public static int calculateTotalStockValue(
            int[] prices,
            int[] stocks) {

        int totalValue = 0;

        for (int i = 0; i < prices.length; i++) {
            totalValue += prices[i] * stocks[i];
        }

        return totalValue;
    }

    public static void printSummary(
            int purchaseCount,
            int restockCount,
            int totalPurchasedItems,
            int[] prices,
            int[] stocks) {

        int totalValue = calculateTotalStockValue(prices, stocks);

        System.out.println("=== Operation Summary ===");
        System.out.println("Purchase count: " + purchaseCount);
        System.out.println("Restock count: " + restockCount);
        System.out.println("Total purchased items: " + totalPurchasedItems);
        System.out.println("Final stock value: " + totalValue);
    }
}