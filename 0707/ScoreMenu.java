import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine();

        System.out.print("請輸入 Java 成績：");
        double javaScore = scanner.nextDouble();

        System.out.print("請輸入英文成績：");
        double englishScore = scanner.nextDouble();

        System.out.print("請輸入數學成績：");
        double mathScore = scanner.nextDouble();

        double average = (javaScore + englishScore + mathScore) / 3;

        int choice = -1;

        while (choice != 0) {
            System.out.println();
            System.out.println("1. 顯示平均分數");
            System.out.println("2. 顯示及格狀態");
            System.out.println("3. 顯示等第");
            System.out.println("0. 離開");
            System.out.print("請輸入選項：");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("姓名：" + name);
                    System.out.println("平均分數：" + average);
                    break;

                case 2:
                    if (average >= 60) {
                        System.out.println("及格");
                    } else {
                        System.out.println("不及格");
                    }
                    break;

                case 3:
                    if (average >= 90) {
                        System.out.println("等第：一個");
                    } else if (average >= 80) {
                        System.out.println("等第：B");
                    } else if (average >= 70) {
                        System.out.println("等第：C");
                    } else if (average >= 60) {
                        System.out.println("等第：D");
                    } else {
                        System.out.println("等第：F");
                    }
                    break;

                case 0:
                    System.out.println("離開程式");
                    break;

                default:
                    System.out.println("輸入錯誤");
            }
        }

        scanner.close();
    }
}