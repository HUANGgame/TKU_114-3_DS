import java.util.ArrayList;

public class RepairAlgorithms {
    public static ArrayList<RepairTask> copyTasks(ArrayList<RepairTask> tasks) {
        ArrayList<RepairTask> copy = new ArrayList<>();
        for (RepairTask task : tasks) {
            copy.add(task);
        }
        return copy;
    }

    public static void mergeSortByPriorityDescending(ArrayList<RepairTask> tasks) {
        if (tasks.size() <= 1) {
            return;
        }
        ArrayList<RepairTask> temp = copyTasks(tasks);
        mergeSortByPriorityDescending(tasks, temp, 0, tasks.size() - 1);
    }

    private static void mergeSortByPriorityDescending(
        ArrayList<RepairTask> tasks,
        ArrayList<RepairTask> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByPriorityDescending(tasks, temp, left, mid);
        mergeSortByPriorityDescending(tasks, temp, mid + 1, right);
        mergeByPriorityDescending(tasks, temp, left, mid, right);
    }

    private static void mergeByPriorityDescending(
        ArrayList<RepairTask> tasks,
        ArrayList<RepairTask> temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (tasks.get(leftIndex).getPriority() >= tasks.get(rightIndex).getPriority()) {
                temp.set(output, tasks.get(leftIndex));
                leftIndex++;
            } else {
                temp.set(output, tasks.get(rightIndex));
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp.set(output, tasks.get(leftIndex));
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp.set(output, tasks.get(rightIndex));
            rightIndex++;
            output++;
        }

        for (int index = left; index <= right; index++) {
            tasks.set(index, temp.get(index));
        }
    }

    public static RepairTask findById(ArrayList<RepairTask> tasks, String id) {
        for (RepairTask task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public static ArrayList<RepairTask> findAllByDeviceName(ArrayList<RepairTask> tasks, String deviceName) {
        ArrayList<RepairTask> results = new ArrayList<>();
        for (RepairTask task : tasks) {
            if (task.getDeviceName().equalsIgnoreCase(deviceName)) {
                results.add(task);
            }
        }
        return results;
    }
}
