import java.util.Scanner;

public class ScoreReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the student's name before reading numeric scores.
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int javaScore = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int englishScore = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = sc.nextInt();

        // Use 3.0 so the average keeps decimal places.
        double average = (javaScore + englishScore + mathScore) / 3.0;

        System.out.println("=== 成績報表 ===");
        System.out.println("姓名：" + name);
        System.out.println("Java：" + javaScore);
        System.out.println("English：" + englishScore);
        System.out.println("Math：" + mathScore);
        System.out.println("平均：" + average);

        sc.close();
    }
}
