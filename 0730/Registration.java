public class Registration {
    private String id;
    private String name;
    private int order;
    private String status;

    public Registration(String id, String name, int order, String status) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " " + name + " order=" + order + " status=" + status;
    }
}
