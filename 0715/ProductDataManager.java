import java.util.Scanner;

/*
測試案例：
1. Keyboard,890,12：格式正確。
2. Mouse,490,20：格式正確。
3. Keyboard,abc,12：價格不是整數。
4. Keyboard,890,abc：庫存不是整數。
5. Keyboard,890：欄位數不足。
6. Keyboard,890,12,Other：欄位數過多。
7. ,890,12：商品名稱空白。
8. Keyboard,-1,12：價格不能小於 0。
9. Keyboard,890,-2：庫存不能小於 0。
10. 完整搜尋 keyboard：應找到 Keyboard。
11. 部分搜尋 e：應找到多筆商品。
12. 搜尋 Printer：應顯示找不到商品。
*/

public class ProductDataManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
                "Keyboard,890,12",
                "Mouse,490,20",
                "Monitor,5200,5",
                "USB Cable,250,30",
                "Headset,1290,8"
        };

        String[] names = new String[records.length];
        int[] prices = new int[records.length];
        int[] stocks = new int[records.length];

        parseRecords(records, names, prices, stocks);

        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    printProducts(names, prices, stocks);
                    break;

                case 2:
                    System.out.print("請輸入完整商品名稱：");
                    String exactName = sc.nextLine();

                    int index = findExactName(names, exactName);

                    if (index == -1) {
                        System.out.println("找不到商品");
                    } else {
                        printProduct(index, names, prices, stocks);
                    }
                    break;

                case 3:
                    System.out.print("請輸入部分商品名稱：");
                    String keyword = sc.nextLine();

                    printPartialMatches(names, prices, stocks, keyword);
                    break;

                case 4:
                    printLowStockProducts(names, stocks);
                    break;

                case 5:
                    int totalValue = calculateTotalStockValue(prices, stocks);
                    System.out.println("庫存總價值：" + totalValue);
                    break;

                case 6:
                    System.out.print("請輸入新商品資料（名稱,價格,庫存）：");
                    String newRecord = sc.nextLine();

                    validateNewRecord(newRecord);
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
        System.out.println("=== Product Data Menu ===");
        System.out.println("1. 顯示商品表格");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示低庫存商品");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 輸入新商品資料");
        System.out.println("0. 結束");
    }

    public static void parseRecords(
            String[] records,
            String[] names,
            int[] prices,
            int[] stocks) {

        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",", -1);

            names[i] = parts[0].trim();
            prices[i] = Integer.parseInt(parts[1].trim());
            stocks[i] = Integer.parseInt(parts[2].trim());
        }
    }

    public static void printProducts(
            String[] names,
            int[] prices,
            int[] stocks) {

        System.out.println("=== Product Table ===");
        System.out.println("No.\tName\t\tPrice\tStock");

        for (int i = 0; i < names.length; i++) {
            System.out.println(
                    (i + 1) + "\t"
                            + names[i] + "\t\t"
                            + prices[i] + "\t"
                            + stocks[i]
            );
        }
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
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的商品");
        }
    }

    public static void printLowStockProducts(
            String[] names,
            int[] stocks) {

        System.out.println("=== Low Stock Products ===");
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + "：剩餘 " + stocks[i]);
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

        int total = 0;

        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * stocks[i];
        }

        return total;
    }

    public static void validateNewRecord(String record) {
        if (record == null || record.trim().isEmpty()) {
            System.out.println("資料不能是空白");
            return;
        }

        String[] parts = record.split(",", -1);

        if (parts.length != 3) {
            System.out.println("格式錯誤：必須包含名稱、價格、庫存");
            return;
        }

        String name = parts[0].trim();

        if (name.isEmpty()) {
            System.out.println("格式錯誤：商品名稱不能是空白");
            return;
        }

        try {
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            if (price < 0) {
                System.out.println("格式錯誤：價格不能小於 0");
                return;
            }

            if (stock < 0) {
                System.out.println("格式錯誤：庫存不能小於 0");
                return;
            }

            System.out.println("資料格式正確");
            System.out.println("商品名稱：" + name);
            System.out.println("商品價格：" + price);
            System.out.println("商品庫存：" + stock);

        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：價格和庫存必須是整數");
        }
    }
}