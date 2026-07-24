public class NumberHistoryList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 空串列測試 ===");
        list.printList();
        list.printStatistics();

        System.out.println("\n1. 前端新增 20");
        list.addFirst(20);
        list.printList();

        System.out.println("\n2. 前端新增 10");
        list.addFirst(10);
        list.printList();

        System.out.println("\n3. 尾端新增 30");
        list.addLast(30);
        list.printList();

        System.out.println("\n4. 尾端新增 40");
        list.addLast(40);
        list.printList();

        System.out.println("\n5. 搜尋 30");
        printSearchResult(list, 30);

        System.out.println("\n6. 搜尋不存在的 99");
        printSearchResult(list, 99);

        System.out.println("\n7. 刪除中間節點 20");
        list.removeValue(20);
        list.printList();

        System.out.println("\n8. 刪除第一個節點 10");
        list.removeValue(10);
        list.printList();

        System.out.println("\n9. 刪除最後節點 40");
        list.removeValue(40);
        list.printList();

        System.out.println("\n10. 刪除不存在的 99");
        list.removeValue(99);
        list.printList();

        System.out.println("\n=== 最後統計 ===");
        list.printStatistics();

        System.out.println("\n11. 刪除剩下的 30");
        list.removeValue(30);
        list.printList();
        list.printStatistics();

        System.out.println("\n12. 空串列刪除測試");
        list.removeValue(30);
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);

        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;
    }

    public boolean contains(int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            System.out.println("刪除失敗，串列目前是空的");
            return false;
        }

        if (head.data == target) {
            head = head.next;
            size--;

            System.out.println("刪除成功：" + target);
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                size--;

                System.out.println("刪除成功：" + target);
                return true;
            }

            current = current.next;
        }

        System.out.println("刪除失敗，找不到資料：" + target);
        return false;
    }

    public int calculateSum() {
        int sum = 0;
        Node current = head;

        while (current != null) {
            sum += current.data;
            current = current.next;
        }

        return sum;
    }

    public Integer findMaximum() {
        if (head == null) {
            return null;
        }

        int maximum = head.data;
        Node current = head.next;

        while (current != null) {
            if (current.data > maximum) {
                maximum = current.data;
            }

            current = current.next;
        }

        return maximum;
    }

    public Integer findMinimum() {
        if (head == null) {
            return null;
        }

        int minimum = head.data;
        Node current = head.next;

        while (current != null) {
            if (current.data < minimum) {
                minimum = current.data;
            }

            current = current.next;
        }

        return minimum;
    }

    public void printList() {
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

    public void printStatistics() {
        System.out.println("節點數量：" + size);
        System.out.println("數字總和：" + calculateSum());

        Integer maximum = findMaximum();
        Integer minimum = findMinimum();

        if (maximum == null) {
            System.out.println("最大值：無資料");
            System.out.println("最小值：無資料");
        } else {
            System.out.println("最大值：" + maximum);
            System.out.println("最小值：" + minimum);
        }
    }

    public static void printSearchResult(
            NumberHistoryList list,
            int target) {

        if (list.contains(target)) {
            System.out.println("找到資料：" + target);
        } else {
            System.out.println("找不到資料：" + target);
        }
    }
}