import java.util.Arrays;

public class MergeSortPractice {
    public static void mergeSort(int[] values) {
        if (values.length <= 1) {
            System.out.println("stop: length=" + values.length + ", " + Arrays.toString(values));
            return;
        }
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(int[] values, int[] temp, int left, int right) {
        if (left >= right) {
            System.out.println("stop range [" + left + "," + right + "]");
            return;
        }

        int mid = left + (right - left) / 2;
        System.out.println("split [" + left + "," + right + "] -> [" + left + "," + mid + "] and [" + (mid + 1) + "," + right + "]");

        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);

        System.out.println("merged [" + left + "," + right + "] = " + rangeToString(values, left, right));
    }

    public static void merge(int[] values, int[] temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (values[leftIndex] <= values[rightIndex]) {
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

    private static String rangeToString(int[] values, int left, int right) {
        String result = "[";
        for (int index = left; index <= right; index++) {
            if (index > left) {
                result += ", ";
            }
            result += values[index];
        }
        return result + "]";
    }

    private static void test(String label, int[] values) {
        System.out.println(label);
        System.out.println("before=" + Arrays.toString(values));
        mergeSort(values);
        System.out.println("after=" + Arrays.toString(values));
        System.out.println();
    }

    public static void main(String[] args) {
        test("main data", new int[] {41, 12, 35, 8, 27, 19, 50, 3});
        test("empty data", new int[] {});
        test("single data", new int[] {7});
        test("sorted data", new int[] {1, 2, 3, 4, 5});
        test("reverse data", new int[] {5, 4, 3, 2, 1});
    }
}
