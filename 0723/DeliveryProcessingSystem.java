import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> waitingQueue;
    private Stack<DeliveryTask> completedStack;
    private ArrayList<String> processingHistory;

    public DeliveryProcessingSystem() {
        waitingQueue = new LinkedList<>();
        completedStack = new Stack<>();
        processingHistory = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeliveryProcessingSystem system =
                new DeliveryProcessingSystem();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        system.addTask(sc);
                        break;

                    case 2:
                        system.completeNextTask();
                        break;

                    case 3:
                        system.showNextTask();
                        break;

                    case 4:
                        system.moveNextTaskToEnd();
                        break;

                    case 5:
                        system.showWaitingTasks();
                        break;

                    case 6:
                        system.showRecentCompletedTasks(sc);
                        break;

                    case 7:
                        system.showStatistics();
                        break;

                    case 8:
                        system.showProcessingHistory();
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
        System.out.println("=== 報表工作流程系統 ===");
        System.out.println("1. 新增報表工作");
        System.out.println("2. 完成下一個報表");
        System.out.println("3. 檢視下一個報表");
        System.out.println("4. 將下一個報表移到隊尾");
        System.out.println("5. 顯示等待中的報表");
        System.out.println("6. 顯示最近完成的多個報表");
        System.out.println("7. 顯示統計資料");
        System.out.println("8. 顯示所有處理記錄");
        System.out.println("0. 結束");
    }

    public void addTask(Scanner sc) {
        System.out.print("請輸入工作代碼：");
        String taskCode = sc.nextLine().trim();

        if (taskCode.isEmpty()) {
            System.out.println("新增失敗，工作代碼不能是空白");
            return;
        }

        if (taskCodeExists(taskCode)) {
            System.out.println("新增失敗，工作代碼已存在");
            return;
        }

        System.out.print("請輸入報表名稱：");
        String reportName = sc.nextLine().trim();

        if (reportName.isEmpty()) {
            System.out.println("新增失敗，報表名稱不能是空白");
            return;
        }

        DeliveryTask task =
                new DeliveryTask(taskCode, reportName);

        waitingQueue.offer(task);

        String record = "新增工作：" + task;
        processingHistory.add(record);

        System.out.println("新增成功");
        System.out.println(task);
    }

    public void completeNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("完成失敗，目前沒有等待中的報表");
            return;
        }

        DeliveryTask task = waitingQueue.poll();
        task.increaseProcessCount();

        completedStack.push(task);

        String record = "完成工作：" + task;
        processingHistory.add(record);

        System.out.println("報表完成");
        System.out.println(task);
    }

    public void showNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一個報表");
            return;
        }

        System.out.println("下一個報表：");
        System.out.println(waitingQueue.peek());
    }

    public void moveNextTaskToEnd() {
        if (waitingQueue.isEmpty()) {
            System.out.println("移動失敗，目前沒有等待中的報表");
            return;
        }

        DeliveryTask task = waitingQueue.poll();
        task.increaseProcessCount();

        waitingQueue.offer(task);

        String record = "工作重新排隊：" + task;
        processingHistory.add(record);

        System.out.println("工作尚未完成，已移到等待佇列尾端");
        System.out.println(task);
    }

    public void showWaitingTasks() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的報表");
            return;
        }

        System.out.println("=== 等待中的報表 ===");

        int order = 1;

        for (DeliveryTask task : waitingQueue) {
            System.out.println(order + ". " + task);
            order++;
        }

        System.out.println("等待數量：" + waitingQueue.size());
    }

    public void showRecentCompletedTasks(Scanner sc) {
        if (completedStack.isEmpty()) {
            System.out.println("目前沒有完成記錄");
            return;
        }

        System.out.print("請輸入要查看的完成筆數：");

        int count;

        try {
            count = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("輸入錯誤，筆數必須是整數");
            sc.nextLine();
            return;
        }

        if (count <= 0) {
            System.out.println("查看筆數必須大於 0");
            return;
        }

        int actualCount = Math.min(count, completedStack.size());

        System.out.println(
                "=== 最近完成的 " + actualCount + " 個報表 ==="
        );

        for (int i = 0; i < actualCount; i++) {
            int index = completedStack.size() - 1 - i;

            System.out.println(
                    (i + 1) + ". " + completedStack.get(index)
            );
        }
    }

    public void showStatistics() {
        System.out.println("=== 工作統計 ===");
        System.out.println("等待數量：" + waitingQueue.size());
        System.out.println("完成數量：" + completedStack.size());
        System.out.println(
                "處理記錄數量：" + processingHistory.size()
        );
    }

    public void showProcessingHistory() {
        if (processingHistory.isEmpty()) {
            System.out.println("目前沒有任何處理記錄");
            return;
        }

        System.out.println("=== 所有處理記錄 ===");

        for (int i = 0; i < processingHistory.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + processingHistory.get(i)
            );
        }
    }

    public boolean taskCodeExists(String taskCode) {
        for (DeliveryTask task : waitingQueue) {
            if (task.getTaskCode().equalsIgnoreCase(taskCode)) {
                return true;
            }
        }

        for (DeliveryTask task : completedStack) {
            if (task.getTaskCode().equalsIgnoreCase(taskCode)) {
                return true;
            }
        }

        return false;
    }
}