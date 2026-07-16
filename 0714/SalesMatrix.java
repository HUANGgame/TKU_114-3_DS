import java.util.Scanner;

public class SalesMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sales = new int[3][4];

        inputSales(sc, sales);

        System.out.println();
        System.out.println("=== Sales Table ===");
        printSales(sales);

        System.out.println();
        System.out.println("=== Product Totals ===");

        for (int row = 0; row < sales.length; row++) {
            int total = calculateProductTotal(sales, row);
            System.out.println("Product " + (row + 1) + ": " + total);
        }

        System.out.println();
        System.out.println("=== Daily Totals ===");

        for (int col = 0; col < sales[0].length; col++) {
            int total = calculateDayTotal(sales, col);
            System.out.println("Day " + (col + 1) + ": " + total);
        }

        int bestProductIndex = findBestProduct(sales);

        System.out.println();
        System.out.println("Highest sales product: Product "
                + (bestProductIndex + 1));
        System.out.println("Total sales: "
                + calculateProductTotal(sales, bestProductIndex));

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int row = 0; row < sales.length; row++) {
            for (int col = 0; col < sales[row].length; col++) {
                System.out.print("請輸入商品 " + (row + 1)
                        + " 第 " + (col + 1) + " 天的銷售量：");

                int value = sc.nextInt();

                while (value < 0) {
                    System.out.print("銷售量不能小於 0，請重新輸入：");
                    value = sc.nextInt();
                }

                sales[row][col] = value;
            }
        }
    }

    public static void printSales(int[][] sales) {
        System.out.print("\t");

        for (int col = 0; col < sales[0].length; col++) {
            System.out.print("Day " + (col + 1) + "\t");
        }

        System.out.println();

        for (int row = 0; row < sales.length; row++) {
            System.out.print("Product " + (row + 1) + "\t");

            for (int col = 0; col < sales[row].length; col++) {
                System.out.print(sales[row][col] + "\t");
            }

            System.out.println();
        }
    }

    public static int calculateProductTotal(int[][] sales, int row) {
        int total = 0;

        for (int col = 0; col < sales[row].length; col++) {
            total += sales[row][col];
        }

        return total;
    }

    public static int calculateDayTotal(int[][] sales, int col) {
        int total = 0;

        for (int row = 0; row < sales.length; row++) {
            total += sales[row][col];
        }

        return total;
    }

    public static int findBestProduct(int[][] sales) {
        int bestProductIndex = 0;
        int highestTotal = calculateProductTotal(sales, 0);

        for (int row = 1; row < sales.length; row++) {
            int total = calculateProductTotal(sales, row);

            if (total > highestTotal) {
                highestTotal = total;
                bestProductIndex = row;
            }
        }

        return bestProductIndex;
    }
}