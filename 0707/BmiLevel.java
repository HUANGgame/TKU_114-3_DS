import java.util.Scanner;

public class BmiLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入 BMI：");
        double bmi = sc.nextDouble();

        if (bmi < 18.5) {
            System.out.println("BMI level: Underweight");
        } else if (bmi < 24) {
            System.out.println("BMI level: Normal");
        } else if (bmi < 27) {
            System.out.println("BMI level: Overweight");
        } else {
            System.out.println("BMI level: Obese");
        }

        sc.close();
    }
}
