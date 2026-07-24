import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskLinkedListSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskLinkedList taskList = new TaskLinkedList();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addUrgentTask(sc, taskList);
                        break;

                    case 2:
                        addNormalTask(sc, taskList);
                        break;

                    case 3:
                        completeTask(sc, taskList);
                        break;

                    case 4:
                        deleteTask(sc, taskList);
                        break;

                    case 5:
                        taskList.printIncompleteTasks();
                        break;

                    case 6:
                        taskList.printAllTasks();
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println(
                                "選項錯誤，請重新輸入"
                        );
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "輸入錯誤，選項必須是整數"
                );
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 工作項目系統 ===");
        System.out.println("1. 新增緊急工作");
        System.out.println("2. 新增一般工作");
        System.out.println("3. 完成工作");
        System.out.println("4. 刪除工作");
        System.out.println("5. 列出未完成工作");
        System.out.println("6. 列出全部工作");
        System.out.println("0. 結束");
    }

    public static void addUrgentTask(
            Scanner sc,
            TaskLinkedList taskList) {

        String code = readCode(sc);

        System.out.print("請輸入工作說明：");
        String description = sc.nextLine();

        taskList.addFirst(code, description);
    }

    public static void addNormalTask(
            Scanner sc,
            TaskLinkedList taskList) {

        String code = readCode(sc);

        System.out.print("請輸入工作說明：");
        String description = sc.nextLine();

        taskList.addLast(code, description);
    }

    public static void completeTask(
            Scanner sc,
            TaskLinkedList taskList) {

        String code = readCode(sc);
        taskList.completeTask(code);
    }

    public static void deleteTask(
            Scanner sc,
            TaskLinkedList taskList) {

        String code = readCode(sc);
        taskList.removeByCode(code);

        System.out.println("刪除後的工作串列：");
        taskList.printAllTasks();
    }

    public static String readCode(Scanner sc) {
        System.out.print("請輸入工作代碼：");
        return sc.nextLine().trim();
    }
}