import java.util.ArrayList;

public class RegistrationAlgorithms {
    public static ArrayList<Registration> copy(ArrayList<Registration> data) {
        ArrayList<Registration> result = new ArrayList<Registration>();
        for (Registration item : data) {
            result.add(item);
        }
        return result;
    }

    public static void mergeSortByOrder(ArrayList<Registration> data) {
        if (data.size() <= 1) {
            return;
        }
        ArrayList<Registration> temp = new ArrayList<Registration>();
        for (int i = 0; i < data.size(); i++) {
            temp.add(null);
        }
        mergeSortByOrder(data, temp, 0, data.size() - 1);
    }

    private static void mergeSortByOrder(ArrayList<Registration> data,
            ArrayList<Registration> temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByOrder(data, temp, left, mid);
        mergeSortByOrder(data, temp, mid + 1, right);
        mergeByOrder(data, temp, left, mid, right);
    }

    private static void mergeByOrder(ArrayList<Registration> data,
            ArrayList<Registration> temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (data.get(leftIndex).getOrder() <= data.get(rightIndex).getOrder()) {
                temp.set(output, data.get(leftIndex));
                leftIndex++;
            } else {
                temp.set(output, data.get(rightIndex));
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp.set(output, data.get(leftIndex));
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp.set(output, data.get(rightIndex));
            rightIndex++;
            output++;
        }

        for (int i = left; i <= right; i++) {
            data.set(i, temp.get(i));
        }
    }

    public static void mergeSortById(ArrayList<Registration> data) {
        if (data.size() <= 1) {
            return;
        }
        ArrayList<Registration> temp = new ArrayList<Registration>();
        for (int i = 0; i < data.size(); i++) {
            temp.add(null);
        }
        mergeSortById(data, temp, 0, data.size() - 1);
    }

    private static void mergeSortById(ArrayList<Registration> data,
            ArrayList<Registration> temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortById(data, temp, left, mid);
        mergeSortById(data, temp, mid + 1, right);
        mergeById(data, temp, left, mid, right);
    }

    private static void mergeById(ArrayList<Registration> data,
            ArrayList<Registration> temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (data.get(leftIndex).getId().compareTo(data.get(rightIndex).getId()) <= 0) {
                temp.set(output, data.get(leftIndex));
                leftIndex++;
            } else {
                temp.set(output, data.get(rightIndex));
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp.set(output, data.get(leftIndex));
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp.set(output, data.get(rightIndex));
            rightIndex++;
            output++;
        }

        for (int i = left; i <= right; i++) {
            data.set(i, temp.get(i));
        }
    }

    public static int binarySearchById(ArrayList<Registration> sortedData, String id) {
        int low = 0;
        int high = sortedData.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = sortedData.get(mid).getId().compareTo(id);

            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static ArrayList<Registration> searchByName(ArrayList<Registration> data, String name) {
        ArrayList<Registration> result = new ArrayList<Registration>();
        for (Registration item : data) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    public static boolean containsId(ArrayList<Registration> data, String id) {
        for (Registration item : data) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
