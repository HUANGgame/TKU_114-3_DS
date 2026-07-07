import java.util.Scanner;

public class PassCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入分數：");
        int score = sc.nextInt();
        System.out.println("Score: " + score);

        if (score >= 60) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        sc.close();
    }
}
