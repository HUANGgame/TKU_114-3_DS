public class Employee {
    private String id;
    private String name;
    private String department;
    private String extension;

    public Employee(String id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + department + " ext=" + extension;
    }
}
