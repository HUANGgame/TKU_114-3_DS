import java.util.Scanner;


public class ProductSearchSystem {
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

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    printAllProducts(names, prices, stocks);
                    break;

                case 2:
                    System.out.print("請輸入完整商品名稱：");
                    String exactKeyword = sc.nextLine();

                    int exactIndex = findExactName(names, exactKeyword);

                    if (exactIndex == -1) {
                        System.out.println("找不到商品");
                    } else {
                        printProduct(exactIndex, names, prices, stocks);
                    }
                    break;

                case 3:
                    System.out.print("請輸入部分商品名稱：");
                    String partialKeyword = sc.nextLine();

                    printPartialMatches(
                            names,
                            prices,
                            stocks,
                            partialKeyword
                    );
                    break;

                case 4:
                    int longestIndex = findLongestNameIndex(names);

                    System.out.println("名稱最長的商品：");
                    printProduct(longestIndex, names, prices, stocks);
                    break;

                case 5:
                    System.out.print("請輸入商品完整名稱：");
                    String productName = sc.nextLine();

                    int productIndex = findExactName(names, productName);

                    if (productIndex == -1) {
                        System.out.println("找不到商品");
                    } else {
                        System.out.print("請輸入搜尋關鍵字：");
                        String searchKeyword = sc.nextLine();

                        int position = findKeywordPosition(
                                names[productIndex],
                                searchKeyword
                        );

                        System.out.println("第一次出現的位置：" + position);
                    }
                    break;

                case 0:
                    System.out.println("程式結束");
                    break;

                default:
                    System.out.println("選項錯誤");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Product Search Menu ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示關鍵字第一次出現的位置");
        System.out.println("0. 結束");
    }

    public static void printAllProducts(
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println("=== Product List ===");

        for (int i = 0; i < names.length; i++) {
            printProduct(i, names, prices, stocks);
        }
    }

    public static void printProduct(
            int index,
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println(
                (index + 1) + ". " + names[index]
                        + " | Price: " + prices[index]
                        + " | Stock: " + stocks[index]
        );
    }

    public static int findExactName(String[] names, String keyword) {
        String cleanedKeyword = keyword.trim();

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(cleanedKeyword)) {
                return i;
            }
        }

        return -1;
    }

    public static void printPartialMatches(
            String[] names,
            int[] prices,
            int[] stocks,
            String keyword) {

        String cleanedKeyword = keyword.trim().toLowerCase();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(cleanedKeyword)) {
                printProduct(i, names, prices, stocks);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的商品");
        }
    }

    public static int findLongestNameIndex(String[] names) {
        int longestIndex = 0;

        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > names[longestIndex].length()) {
                longestIndex = i;
            }
        }

        return longestIndex;
    }

    public static int findKeywordPosition(
            String productName,
            String keyword) {

        String lowerName = productName.toLowerCase();
        String lowerKeyword = keyword.trim().toLowerCase();

        return lowerName.indexOf(lowerKeyword);
    }
}