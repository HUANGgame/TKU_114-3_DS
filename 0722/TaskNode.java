public class TaskNode {
    private String code;
    private String description;
    private boolean completed;
    TaskNode next;

    public static void main(String[] args) {
        TaskLinkedListSystem.main(args);
    }

    public TaskNode(String code, String description) {
        this.code = code.trim();
        this.description = description.trim();
        this.completed = false;
        this.next = null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean completeTask() {
        if (completed) {
            return false;
        }

        completed = true;
        return true;
    }

    @Override
    public String toString() {
        String status;

        if (completed) {
            status = "已完成";
        } else {
            status = "未完成";
        }

        return code + " | " + description + " | " + status;
    }
}
