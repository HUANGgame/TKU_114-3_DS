import java.util.ArrayDeque;
import java.util.ArrayList;

public class RepairSchedulingSystem {
    private static ArrayDeque<RepairTask> waitingQueue = new ArrayDeque<>();
    private static ArrayDeque<RepairTask> completedStack = new ArrayDeque<>();
    private static ArrayList<RepairTask> allTasks = new ArrayList<>();

    public static void addTask(RepairTask task) {
        allTasks.add(task);
        waitingQueue.addLast(task);
    }

    public static RepairTask completeNextTask() {
        RepairTask task = waitingQueue.pollFirst();
        if (task != null) {
            completedStack.push(task);
        }
        return task;
    }

    public static RepairTask undoCompletedTask() {
        RepairTask task = completedStack.poll();
        if (task != null) {
            waitingQueue.addFirst(task);
        }
        return task;
    }

    public static void printTasks(String title, ArrayList<RepairTask> tasks) {
        System.out.println(title);
        if (tasks.isEmpty()) {
            System.out.println("(empty)");
        }
        for (RepairTask task : tasks) {
            System.out.println(task);
        }
        System.out.println();
    }

    public static void printStatistics() {
        System.out.println("waiting count=" + waitingQueue.size());
        System.out.println("completed count=" + completedStack.size());
        System.out.println("all task count=" + allTasks.size());
        System.out.println();
    }

    public static void main(String[] args) {
        printStatistics();
        System.out.println("complete empty queue=" + completeNextTask());
        System.out.println("undo empty stack=" + undoCompletedTask());
        System.out.println();

        addTask(new RepairTask("R105", "Printer", 3, "paper jam"));
        addTask(new RepairTask("R101", "Laptop", 5, "cannot boot"));
        addTask(new RepairTask("R108", "Router", 4, "unstable network"));
        addTask(new RepairTask("R103", "Laptop", 5, "battery issue"));
        addTask(new RepairTask("R110", "Monitor", 2, "no signal"));
        addTask(new RepairTask("R102", "Printer", 1, "low ink"));

        printStatistics();

        ArrayList<RepairTask> sorted = RepairAlgorithms.copyTasks(allTasks);
        RepairAlgorithms.mergeSortByPriorityDescending(sorted);
        printTasks("sorted by priority descending:", sorted);

        System.out.println("find id R108=" + RepairAlgorithms.findById(allTasks, "R108"));
        System.out.println("find id R999=" + RepairAlgorithms.findById(allTasks, "R999"));
        printTasks("find device Laptop:", RepairAlgorithms.findAllByDeviceName(allTasks, "Laptop"));
        printTasks("find device Scanner:", RepairAlgorithms.findAllByDeviceName(allTasks, "Scanner"));

        System.out.println("complete=" + completeNextTask());
        System.out.println("complete=" + completeNextTask());
        printStatistics();

        System.out.println("undo=" + undoCompletedTask());
        printStatistics();
    }
}
