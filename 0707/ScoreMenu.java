import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int javaScore = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int englishScore = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = sc.nextInt();

        double average = (javaScore + englishScore + mathScore) / 3.0;
        String passStatus;
        String grade;

        if (average >= 60) {
            passStatus = "Pass";
        } else {
            passStatus = "Fail";
        }

        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;

        while (option != 0) {
            System.out.println("=== Score Menu ===");
            System.out.println("1. Average");
            System.out.println("2. Pass status");
            System.out.println("3. Grade");
            System.out.println("0. Exit");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println(name + " average: " + average);
                    break;
                case 2:
                    System.out.println(name + " status: " + passStatus);
                    break;
                case 3:
                    System.out.println(name + " grade: " + grade);
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }

        sc.close();
    }
}
