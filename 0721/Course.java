public class Course {
    private String code;
    private String name;
    private int capacity;
    private int currentStudents;

    public static void main(String[] args) {
        CourseCrudSystem.main(args);
    }

    public Course(String code, String name, int capacity) {
        this.code = code.trim();
        this.name = name.trim();
        this.capacity = capacity;
        this.currentStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public boolean register() {
        if (currentStudents >= capacity) {
            return false;
        }

        currentStudents++;
        return true;
    }

    public boolean withdraw() {
        if (currentStudents <= 0) {
            return false;
        }

        currentStudents--;
        return true;
    }

    public boolean isFull() {
        return currentStudents >= capacity;
    }

    @Override
    public String toString() {
        String status;

        if (isFull()) {
            status = "已額滿";
        } else {
            status = "可選課";
        }

        return code
                + " | " + name
                + " | 容量：" + capacity
                + " | 目前人數：" + currentStudents
                + " | " + status;
    }
}
