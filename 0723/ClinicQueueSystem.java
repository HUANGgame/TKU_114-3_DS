import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class ClinicQueueSystem {
    private Queue<Patient> waitingQueue;
    private ArrayList<Patient> serviceHistory;
    private Set<Integer> registeredNumbers;

    public ClinicQueueSystem() {
        waitingQueue = new LinkedList<>();
        serviceHistory = new ArrayList<>();
        registeredNumbers = new HashSet<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClinicQueueSystem system = new ClinicQueueSystem();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        system.registerPatient(sc);
                        break;

                    case 2:
                        system.callNextPatient();
                        break;

                    case 3:
                        system.showNextPatient();
                        break;

                    case 4:
                        system.showWaitingList();
                        break;

                    case 5:
                        system.showDepartmentWaitingCount();
                        break;

                    case 6:
                        system.showStatistics();
                        break;

                    case 0:
                        System.out.println("系統結束");
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
        System.out.println("=== 診所叫號系統 ===");
        System.out.println("1. 患者掛號");
        System.out.println("2. 叫號");
        System.out.println("3. 查看下一位");
        System.out.println("4. 顯示等待清單");
        System.out.println("5. 顯示各科別等待人數");
        System.out.println("6. 顯示統計資料");
        System.out.println("0. 結束");
    }

    public void registerPatient(Scanner sc) {
        int number = readPositiveNumber(sc);

        if (registeredNumbers.contains(number)) {
            System.out.println("掛號失敗，號碼不可重複");
            return;
        }

        System.out.print("請輸入患者姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("掛號失敗，姓名不能是空白");
            return;
        }

        System.out.print("請輸入科別：");
        String department = sc.nextLine().trim();

        if (department.isEmpty()) {
            System.out.println("掛號失敗，科別不能是空白");
            return;
        }

        Patient patient = new Patient(number, name, department);

        waitingQueue.offer(patient);
        registeredNumbers.add(number);

        System.out.println("掛號成功");
        System.out.println(patient);
    }

    public void callNextPatient() {
        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗，目前沒有等待中的患者");
            return;
        }

        Patient patient = waitingQueue.poll();
        serviceHistory.add(patient);

        System.out.println("現在叫號：" + patient);
        System.out.println("請患者前往診間");
    }

    public void showNextPatient() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一位患者");
            return;
        }

        System.out.println("下一位患者：" + waitingQueue.peek());
    }

    public void showWaitingList() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的患者");
            return;
        }

        System.out.println("=== 患者等待清單 ===");

        int order = 1;

        for (Patient patient : waitingQueue) {
            System.out.println(order + ". " + patient);
            order++;
        }

        System.out.println("目前等待人數：" + waitingQueue.size());
    }

    public void showDepartmentWaitingCount() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的患者");
            return;
        }

        Map<String, Integer> departmentCounts =
                new LinkedHashMap<>();

        for (Patient patient : waitingQueue) {
            String department = patient.getDepartment();

            departmentCounts.put(
                    department,
                    departmentCounts.getOrDefault(department, 0) + 1
            );
        }

        System.out.println("=== 各科別等待人數 ===");

        for (Map.Entry<String, Integer> entry
                : departmentCounts.entrySet()) {

            System.out.println(
                    entry.getKey() + "：" + entry.getValue() + " 人"
            );
        }
    }

    public void showStatistics() {
        System.out.println("=== 服務統計 ===");
        System.out.println("目前等待人數：" + waitingQueue.size());
        System.out.println("服務總人數：" + serviceHistory.size());

        showDepartmentWaitingCount();
    }

    public int readPositiveNumber(Scanner sc) {
        while (true) {
            try {
                System.out.print("請輸入患者號碼：");
                int number = sc.nextInt();
                sc.nextLine();

                if (number > 0) {
                    return number;
                }

                System.out.println("患者號碼必須大於 0");
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，患者號碼必須是整數");
                sc.nextLine();
            }
        }
    }
}