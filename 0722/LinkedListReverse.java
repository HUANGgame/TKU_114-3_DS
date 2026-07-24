public class LinkedListReverse {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        testEmptyList();
        testSingleNode();
        testMultipleNodes();
    }

    public static void testEmptyList() {
        Node head = null;

        System.out.println("=== 空串列測試 ===");
        System.out.print("反轉前：");
        printList(head);

        head = reverse(head);

        System.out.print("反轉後：");
        printList(head);
        System.out.println();
    }

    public static void testSingleNode() {
        Node head = new Node(10);

        System.out.println("=== 單一節點測試 ===");
        System.out.print("反轉前：");
        printList(head);

        head = reverse(head);

        System.out.print("反轉後：");
        printList(head);
        System.out.println();
    }

    public static void testMultipleNodes() {
        Node head = buildList();

        System.out.println("=== 多節點測試 ===");
        System.out.print("反轉前：");
        printList(head);

        head = reverse(head);

        System.out.print("反轉後：");
        printList(head);

        System.out.println("搜尋 30：" + contains(head, 30));
        System.out.println("搜尋 99：" + contains(head, 99));
    }

    public static Node buildList() {
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        return node1;
    }

    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;

            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    public static boolean contains(Node head, int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列目前是空的");
            return;
        }

        Node current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}