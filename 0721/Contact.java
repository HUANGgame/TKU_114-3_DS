public class Contact {
    private String code;
    private String name;
    private String phone;
    private String email;

    public static void main(String[] args) {
        ContactBookSystem.main(args);
    }

    public Contact(String code, String name, String phone, String email) {
        this.code = code.trim();
        this.name = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        this.phone = phone.trim();
        return true;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | " + phone + " | " + email;
    }
}
