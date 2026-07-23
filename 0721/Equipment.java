public class Equipment {
    private String code;
    private String name;
    private boolean available;

    public static void main(String[] args) {
        EquipmentManager.main(args);
    }

    public Equipment(String code, String name) {
        this.code = code.trim();
        this.name = name.trim();
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean borrowEquipment() {
        if (!available) {
            return false;
        }

        available = false;
        return true;
    }

    public boolean returnEquipment() {
        if (available) {
            return false;
        }

        available = true;
        return true;
    }

    @Override
    public String toString() {
        String status;

        if (available) {
            status = "可借用";
        } else {
            status = "已借出";
        }

        return code + " | " + name + " | " + status;
    }
}
