public class BuildLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node head = node1;

        System.out.println("=== 鏈結串列內容 ===");
        printList(head);

        System.out.println("節點數量：" + countNodes(head));
        System.out.println("節點總和：" + calculateSum(head));

        searchNode(head, 30);
        searchNode(head, 50);

        System.out.println();
        System.out.println("=== 空串列測試 ===");

        Node emptyHead = null;

        printList(emptyHead);
        System.out.println("節點數量：" + countNodes(emptyHead));
        System.out.println("節點總和：" + calculateSum(emptyHead));
        searchNode(emptyHead, 10);
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("串列目前是空的");
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

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static int calculateSum(Node head) {
        int sum = 0;
        Node current = head;

        while (current != null) {
            sum += current.data;
            current = current.next;
        }

        return sum;
    }

    public static Node findNode(Node head, int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public static void searchNode(Node head, int target) {
        if (head == null) {
            System.out.println("搜尋失敗，串列目前是空的");
            return;
        }

        Node result = findNode(head, target);

        if (result == null) {
            System.out.println("找不到資料：" + target);
        } else {
            System.out.println("找到資料：" + result.data);
        }
    }
}