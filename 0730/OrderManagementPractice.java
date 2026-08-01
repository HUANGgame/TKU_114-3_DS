import java.util.ArrayDeque;
import java.util.ArrayList;

public class OrderManagementPractice {
    private static ArrayList<Order> mainOrders = new ArrayList<>();
    private static ArrayDeque<Order> waitingQueue = new ArrayDeque<>();
    private static ArrayDeque<Order> completedStack = new ArrayDeque<>();

    public static boolean addOrder(Order order) {
        if (order == null || containsOrderId(order.getId())) {
            return false;
        }
        mainOrders.add(order);
        waitingQueue.addLast(order);
        return true;
    }

    private static boolean containsOrderId(String id) {
        for (Order order : mainOrders) {
            if (order.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Order peekNextWaitingOrder() {
        return waitingQueue.peekFirst();
    }

    public static Order processNextOrder() {
        Order order = waitingQueue.pollFirst();
        if (order != null) {
            completedStack.push(order);
        }
        return order;
    }

    public static Order peekLastCompletedOrder() {
        return completedStack.peek();
    }

    public static void printOrders(String title, ArrayList<Order> orders) {
        System.out.println(title);
        if (orders.isEmpty()) {
            System.out.println("(empty)");
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void printArray(String title, Order[] orders) {
        System.out.println(title);
        if (orders.length == 0) {
            System.out.println("(empty)");
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static Order[] copyMainOrders() {
        Order[] copy = new Order[mainOrders.size()];
        for (int index = 0; index < mainOrders.size(); index++) {
            copy[index] = mainOrders.get(index);
        }
        return copy;
    }

    public static void main(String[] args) {
        System.out.println("empty queue next=" + peekNextWaitingOrder());
        System.out.println("empty stack lastCompleted=" + peekLastCompletedOrder());

        System.out.println("add O100=" + addOrder(new Order("O100", "Amy", 1500)));
        System.out.println("add O101=" + addOrder(new Order("O101", "Ben", 4200)));
        System.out.println("add O102=" + addOrder(new Order("O102", "Amy", 3000)));
        System.out.println("add O103=" + addOrder(new Order("O103", "Cindy", 4200)));
        System.out.println("add duplicate O101=" + addOrder(new Order("O101", "David", 9000)));

        printOrders("main orders:", mainOrders);
        System.out.println("next waiting=" + peekNextWaitingOrder());

        Order[] sortedByAmount = copyMainOrders();
        OrderAlgorithms.mergeSortByAmountDescending(sortedByAmount);
        printArray("sorted by amount descending:", sortedByAmount);

        printOrders("customer Amy:", OrderAlgorithms.findByCustomer(mainOrders, "Amy"));
        printOrders("customer Tom:", OrderAlgorithms.findByCustomer(mainOrders, "Tom"));

        System.out.println("process=" + processNextOrder());
        System.out.println("next waiting=" + peekNextWaitingOrder());
        System.out.println("last completed=" + peekLastCompletedOrder());
    }
}
