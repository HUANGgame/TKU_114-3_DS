import java.util.Scanner;

public class MenuDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Show BMI");
        System.out.println("2. Show score average");
        System.out.println("3. Show grade level");
        System.out.print("請輸入選項：");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("Show BMI");
                break;
            case 2:
                System.out.println("Show score average");
                break;
            case 3:
                System.out.println("Show grade level");
                break;
            default:
                System.out.println("Unknown option");
        }

        sc.close();
    }
}
