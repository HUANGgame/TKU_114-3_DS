public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public boolean addFirst(String code, String description) {
        if (!isValidInput(code, description)) {
            return false;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，工作代碼已存在");
            return false;
        }

        TaskNode newNode = new TaskNode(code, description);

        newNode.next = head;
        head = newNode;
        size++;

        System.out.println("緊急工作新增成功：" + newNode);
        return true;
    }

    public boolean addLast(String code, String description) {
        if (!isValidInput(code, description)) {
            return false;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，工作代碼已存在");
            return false;
        }

        TaskNode newNode = new TaskNode(code, description);

        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;

        System.out.println("一般工作新增成功：" + newNode);
        return true;
    }

    private boolean isValidInput(String code, String description) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("新增失敗，工作代碼不能是空白");
            return false;
        }

        if (description == null || description.trim().isEmpty()) {
            System.out.println("新增失敗，工作說明不能是空白");
            return false;
        }

        return true;
    }

    public TaskNode findByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        TaskNode current = head;

        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code.trim())) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public boolean completeTask(String code) {
        if (head == null) {
            System.out.println("完成失敗，工作串列目前是空的");
            return false;
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("完成失敗，工作代碼不能是空白");
            return false;
        }

        TaskNode task = findByCode(code);

        if (task == null) {
            System.out.println("完成失敗，找不到工作代碼：" + code.trim());
            return false;
        }

        if (!task.completeTask()) {
            System.out.println("此工作原本就已經完成");
            return false;
        }

        System.out.println("工作完成：" + task);
        return true;
    }

    public boolean removeByCode(String code) {
        if (head == null) {
            System.out.println("刪除失敗，工作串列目前是空的");
            return false;
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("刪除失敗，工作代碼不能是空白");
            return false;
        }

        String targetCode = code.trim();

        if (head.getCode().equalsIgnoreCase(targetCode)) {
            String deletedDescription = head.getDescription();
            head = head.next;
            size--;

            System.out.println("刪除成功：" + deletedDescription);
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.getCode().equalsIgnoreCase(targetCode)) {
                String deletedDescription =
                        current.next.getDescription();

                current.next = current.next.next;
                size--;

                System.out.println(
                        "刪除成功：" + deletedDescription
                );
                return true;
            }

            current = current.next;
        }

        System.out.println(
                "刪除失敗，找不到工作代碼：" + targetCode
        );
        return false;
    }

    public int countIncompleteTasks() {
        int incompleteCount = 0;
        TaskNode current = head;

        while (current != null) {
            if (!current.isCompleted()) {
                incompleteCount++;
            }

            current = current.next;
        }

        return incompleteCount;
    }

    public void printIncompleteTasks() {
        if (head == null) {
            System.out.println("工作串列目前是空的");
            return;
        }

        boolean found = false;
        TaskNode current = head;
        int number = 1;

        System.out.println("=== 未完成工作 ===");

        while (current != null) {
            if (!current.isCompleted()) {
                System.out.println(number + ". " + current);
                number++;
                found = true;
            }

            current = current.next;
        }

        if (!found) {
            System.out.println("目前沒有未完成工作");
        }

        printStatistics();
    }

    public void printAllTasks() {
        if (head == null) {
            System.out.println("工作串列目前是空的");
            printStatistics();
            return;
        }

        TaskNode current = head;
        int number = 1;

        System.out.println("=== 全部工作 ===");

        while (current != null) {
            System.out.println(number + ". " + current);
            current = current.next;
            number++;
        }

        printStatistics();
    }

    public void printStatistics() {
        System.out.println("工作總數：" + size);
        System.out.println(
                "未完成數量：" + countIncompleteTasks()
        );
    }

    public boolean isEmpty() {
        return head == null;
    }
}