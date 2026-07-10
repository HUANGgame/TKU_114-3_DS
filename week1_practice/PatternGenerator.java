import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;

                case 2:
                    int height = readPositiveInt(sc, "請輸入高度：");
                    printTriangle(height);
                    break;

                case 3:
                    int reverseHeight = readPositiveInt(sc, "請輸入高度：");
                    printReverseTriangle(reverseHeight);
                    break;

                case 4:
                    int rows = readPositiveInt(sc, "請輸入列數：");
                    int cols = readPositiveInt(sc, "請輸入欄數：");
                    printNumberGrid(rows, cols);
                    break;

                case 0:
                    System.out.println("離開程式");
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Pattern Menu ===");
        System.out.println("1. Multiplication table");
        System.out.println("2. Triangle");
        System.out.println("3. Reverse triangle");
        System.out.println("4. Number grid");
        System.out.println("0. Exit");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int number = sc.nextInt();

        while (number <= 0) {
            System.out.print("數字必須大於 0，請重新輸入：");
            number = sc.nextInt();
        }

        return number;
    }

    public static void printMultiplicationTable() {
        for (int row = 1; row <= 9; row++) {
            for (int number = 1; number <= 9; number++) {
                System.out.print(number + " x " + row + " = "
                        + (number * row) + "\t");
            }

            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int row = 1; row <= height; row++) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int row = height; row >= 1; row--) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int row = 1; row <= rows; row++) {
            for (int number = 1; number <= cols; number++) {
                System.out.print(number + " ");
            }

            System.out.println();
        }
    }
}