public class RepairTask {
    private String id;
    private String deviceName;
    private int priority;
    private String problem;

    public RepairTask(String id, String deviceName, int priority, String problem) {
        this.id = id;
        this.deviceName = deviceName;
        this.priority = priority;
        this.problem = problem;
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPriority() {
        return priority;
    }

    public String getProblem() {
        return problem;
    }

    @Override
    public String toString() {
        return id
            + " device=" + deviceName
            + " priority=" + priority
            + " problem=" + problem;
    }
}
