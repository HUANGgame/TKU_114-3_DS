public class LinkedListSearchRemove {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = buildList();

        System.out.println("原始鏈結串列：");
        printList(head);

        System.out.println("搜尋 30：" + contains(head, 30));
        System.out.println("搜尋 99：" + contains(head, 99));

        System.out.println("\n刪除 head 10：");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n刪除中間節點 30：");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n刪除最後節點 40：");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n刪除不存在的 99：");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n刪除剩下的 20：");
        head = removeValue(head, 20);
        printList(head);

        System.out.println("\n空串列搜尋與刪除：");
        System.out.println("搜尋 10：" + contains(head, 10));
        head = removeValue(head, 10);
        printList(head);
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

    public static Node removeValue(Node head, int target) {
        if (head == null) {
            System.out.println("刪除失敗，鏈結串列是空的");
            return null;
        }

        if (head.data == target) {
            System.out.println("刪除成功：" + target);
            return head.next;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                System.out.println("刪除成功：" + target);
                return head;
            }

            current = current.next;
        }

        System.out.println("刪除失敗，找不到資料：" + target);
        return head;
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