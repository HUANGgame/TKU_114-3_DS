import java.util.Arrays;

public class InventorySearchPractice {
    public static void mergeSort(String[] values) {
        if (values.length <= 1) {
            return;
        }
        String[] temp = new String[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(String[] values, String[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    public static void merge(String[] values, String[] temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (values[leftIndex].compareTo(values[rightIndex]) <= 0) {
                temp[output] = values[leftIndex];
                leftIndex++;
            } else {
                temp[output] = values[rightIndex];
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp[output] = values[leftIndex];
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp[output] = values[rightIndex];
            rightIndex++;
            output++;
        }

        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }

    public static int binarySearch(String[] values, String target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = values[mid].compareTo(target);

            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static void printSearch(String[] values, String target) {
        int index = binarySearch(values, target);
        System.out.println("target=" + target + ", index=" + index);
    }

    public static void main(String[] args) {
        String[] inventoryIds = {
            "INV-330", "INV-105", "INV-420", "INV-210",
            "INV-150", "INV-999", "INV-305", "INV-010",
            "INV-640", "INV-275", "INV-500", "INV-075"
        };

        System.out.println("before=" + Arrays.toString(inventoryIds));
        mergeSort(inventoryIds);
        System.out.println("after=" + Arrays.toString(inventoryIds));

        printSearch(inventoryIds, inventoryIds[0]);
        printSearch(inventoryIds, inventoryIds[inventoryIds.length - 1]);
        printSearch(inventoryIds, "INV-777");
    }
}
