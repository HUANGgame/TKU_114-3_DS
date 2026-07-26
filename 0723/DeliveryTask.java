public class DeliveryTask {
    private String taskCode;
    private String reportName;
    private int processCount;

    public DeliveryTask(String taskCode, String reportName) {
        this.taskCode = taskCode;
        this.reportName = reportName;
        this.processCount = 0;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public String getReportName() {
        return reportName;
    }

    public int getProcessCount() {
        return processCount;
    }

    public void increaseProcessCount() {
        processCount++;
    }

    @Override
    public String toString() {
        return "工作代碼：" + taskCode
                + "｜報表名稱：" + reportName
                + "｜處理次數：" + processCount;
    }
}