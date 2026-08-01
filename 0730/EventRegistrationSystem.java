import java.util.ArrayDeque;
import java.util.ArrayList;

public class EventRegistrationSystem {
    static final int CAPACITY = 3;
    static ArrayList<Registration> allRegistrations = new ArrayList<Registration>();
    static ArrayDeque<Registration> waitingQueue = new ArrayDeque<Registration>();
    static ArrayDeque<Registration> cancelledStack = new ArrayDeque<Registration>();
    static int confirmedCount = 0;
    static int nextOrder = 1;

    public static void main(String[] args) {
        System.out.println("=== 活動報名系統 ===");

        cancelRegistration("E999");
        fillNextFromWaiting();

        addRegistration("E103", "Amy");
        addRegistration("E101", "Ben");
        addRegistration("E105", "Cindy");
        addRegistration("E102", "David");
        addRegistration("E104", "Amy");
        addRegistration("E101", "Repeat");

        printStatistics();
        printWaitingQueue();

        cancelRegistration("E101");
        cancelRegistration("E777");
        printStatistics();
        printWaitingQueue();
        printCancelledStack();

        fillNextFromWaiting();
        printStatistics();
        printWaitingQueue();

        printByOrder();
        searchId("E104");
        searchId("E999");
        searchName("Amy");
        searchName("Zoe");
    }

    static void addRegistration(String id, String name) {
        if (RegistrationAlgorithms.containsId(allRegistrations, id)) {
            System.out.println("重複編號，拒絕報名：" + id);
            return;
        }

        String status = "confirmed";
        if (confirmedCount >= CAPACITY) {
            status = "waiting";
        }

        Registration registration = new Registration(id, name, nextOrder, status);
        nextOrder++;
        allRegistrations.add(registration);

        if (status.equals("confirmed")) {
            confirmedCount++;
            System.out.println("報名成功：" + registration);
        } else {
            waitingQueue.offer(registration);
            System.out.println("活動額滿，加入候補：" + registration);
        }
    }

    static void cancelRegistration(String id) {
        Registration target = null;
        for (Registration item : allRegistrations) {
            if (item.getId().equals(id) && !item.getStatus().equals("cancelled")) {
                target = item;
                break;
            }
        }

        if (target == null) {
            System.out.println("取消失敗，找不到可取消資料：" + id);
            return;
        }

        if (target.getStatus().equals("confirmed")) {
            confirmedCount--;
        } else if (target.getStatus().equals("waiting")) {
            waitingQueue.remove(target);
        }

        target.setStatus("cancelled");
        cancelledStack.push(target);
        System.out.println("取消成功：" + target);
    }

    static void fillNextFromWaiting() {
        if (waitingQueue.isEmpty()) {
            System.out.println("候補佇列為空，沒有資料可遞補");
            return;
        }

        if (confirmedCount >= CAPACITY) {
            System.out.println("正式名額仍滿，候補暫不遞補");
            return;
        }

        Registration next = waitingQueue.poll();
        next.setStatus("confirmed");
        confirmedCount++;
        System.out.println("候補遞補成功：" + next);
    }

    static void printByOrder() {
        ArrayList<Registration> sorted = RegistrationAlgorithms.copy(allRegistrations);
        RegistrationAlgorithms.mergeSortByOrder(sorted);

        System.out.println();
        System.out.println("依報名順序排序：");
        for (Registration item : sorted) {
            System.out.println(item);
        }
    }

    static void searchId(String id) {
        ArrayList<Registration> sorted = RegistrationAlgorithms.copy(allRegistrations);
        RegistrationAlgorithms.mergeSortById(sorted);
        int index = RegistrationAlgorithms.binarySearchById(sorted, id);

        if (index >= 0) {
            System.out.println("編號查詢 " + id + "：index=" + index + " " + sorted.get(index));
        } else {
            System.out.println("編號查詢 " + id + "：找不到資料");
        }
    }

    static void searchName(String name) {
        ArrayList<Registration> result = RegistrationAlgorithms.searchByName(allRegistrations, name);

        if (result.isEmpty()) {
            System.out.println("姓名查詢 " + name + "：找不到資料");
            return;
        }

        System.out.println("姓名查詢 " + name + "：");
        for (Registration item : result) {
            System.out.println(item);
        }
    }

    static void printStatistics() {
        int waitingCount = waitingQueue.size();
        int cancelledCount = cancelledStack.size();

        System.out.println();
        System.out.println("統計：");
        System.out.println("全部報名資料：" + allRegistrations.size());
        System.out.println("正式名額：" + confirmedCount);
        System.out.println("候補人數：" + waitingCount);
        System.out.println("取消記錄：" + cancelledCount);
    }

    static void printWaitingQueue() {
        System.out.println("候補佇列：");
        if (waitingQueue.isEmpty()) {
            System.out.println("空");
            return;
        }

        for (Registration item : waitingQueue) {
            System.out.println(item);
        }
    }

    static void printCancelledStack() {
        System.out.println("最近取消記錄 Stack：");
        if (cancelledStack.isEmpty()) {
            System.out.println("空");
            return;
        }

        for (Registration item : cancelledStack) {
            System.out.println(item);
        }
    }
}
