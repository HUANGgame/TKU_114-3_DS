import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CounterServiceSystem {

    static class Customer {
        private int number;
        private String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "號碼：" + number + "，姓名：" + name;
        }
    }

    private Queue<Customer> waitingQueue;
    private ArrayList<Customer> serviceHistory;
    private int nextNumber;

    public CounterServiceSystem() {
        waitingQueue = new LinkedList<>();
        serviceHistory = new ArrayList<>();
        nextNumber = 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CounterServiceSystem system = new CounterServiceSystem();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        system.takeNumber(sc);
                        break;

                    case 2:
                        system.callNextCustomer();
                        break;

                    case 3:
                        system.showNextCustomer();
                        break;

                    case 4:
                        system.showWaitingCustomers();
                        break;

                    case 5:
                        system.showWaitingCount();
                        break;

                    case 6:
                        system.showServiceHistory();
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println("選項錯誤，請重新輸入");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，選項必須是整數");
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 櫃檯叫號系統 ===");
        System.out.println("1. 取號");
        System.out.println("2. 叫號");
        System.out.println("3. 查看下一位");
        System.out.println("4. 顯示等待名單");
        System.out.println("5. 顯示等待人數");
        System.out.println("6. 顯示處理紀錄");
        System.out.println("0. 結束");
    }

    public void takeNumber(Scanner sc) {
        System.out.print("請輸入姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("取號失敗，姓名不能是空白");
            return;
        }

        Customer customer = new Customer(nextNumber, name);
        waitingQueue.offer(customer);

        System.out.println("取號成功");
        System.out.println(customer);

        nextNumber++;
    }

    public void callNextCustomer() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的顧客，無法叫號");
            return;
        }

        Customer customer = waitingQueue.poll();
        serviceHistory.add(customer);

        System.out.println("現在叫號：" + customer);
        System.out.println("請至櫃檯辦理");
    }

    public void showNextCustomer() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一位顧客");
            return;
        }

        Customer nextCustomer = waitingQueue.peek();
        System.out.println("下一位：" + nextCustomer);
    }

    public void showWaitingCustomers() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的顧客");
            return;
        }

        System.out.println("=== 等待名單 ===");

        int order = 1;

        for (Customer customer : waitingQueue) {
            System.out.println(order + ". " + customer);
            order++;
        }

        System.out.println("等待人數：" + waitingQueue.size());
    }

    public void showWaitingCount() {
        System.out.println("目前等待人數：" + waitingQueue.size());
    }

    public void showServiceHistory() {
        if (serviceHistory.isEmpty()) {
            System.out.println("目前沒有處理紀錄");
            return;
        }

        System.out.println("=== 處理紀錄 ===");

        for (int i = 0; i < serviceHistory.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + serviceHistory.get(i)
            );
        }

        System.out.println("已處理人數：" + serviceHistory.size());
    }
}