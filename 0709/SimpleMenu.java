import java.util.Scanner;

public class SimpleMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printMenu();

        System.out.print("請輸入選項：");
        int option = sc.nextInt();

        System.out.println("你選擇的是：" + option);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("1：選項一");
        System.out.println("2：選項二");
        System.out.println("3：選項三");
        System.out.println("0：離開");
    }
}