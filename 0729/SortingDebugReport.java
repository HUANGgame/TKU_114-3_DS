import java.util.Arrays;

public class SortingDebugReport {
    public static int[] copyOf(int[] source) {
        int[] copy = new int[source.length];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index];
        }
        return copy;
    }

    public static void badInnerRangeSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            // Error: index < values.length - 1 skips the last element.
            for (int index = start + 1; index < values.length - 1; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            swap(values, start, minIndex);
        }
    }

    public static void fixedInnerRangeSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            swap(values, start, minIndex);
        }
    }

    public static void badKeyNotSavedInsertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int position = index - 1;
            // Error: key is not saved before shifting, so values[index] can be overwritten.
            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = values[index];
        }
    }

    public static void fixedKeySavedInsertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }

    public static void badDirectionSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int selectedIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                // Error: using > selects the maximum value, producing descending order.
                if (values[index] > values[selectedIndex]) {
                    selectedIndex = index;
                }
            }
            swap(values, start, selectedIndex);
        }
    }

    public static void fixedDirectionSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int selectedIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[selectedIndex]) {
                    selectedIndex = index;
                }
            }
            swap(values, start, selectedIndex);
        }
    }

    public static void swap(int[] values, int a, int b) {
        int temp = values[a];
        values[a] = values[b];
        values[b] = temp;
    }

    public static void runCase(String title, int[] data, boolean useInnerRangeCase) {
        int[] bad = copyOf(data);
        int[] fixed = copyOf(data);

        System.out.println(title);
        System.out.println("original=" + Arrays.toString(data));

        if (useInnerRangeCase) {
            badInnerRangeSelectionSort(bad);
            fixedInnerRangeSelectionSort(fixed);
        } else {
            badDirectionSelectionSort(bad);
            fixedDirectionSelectionSort(fixed);
        }

        System.out.println("bad result=" + Arrays.toString(bad));
        System.out.println("fixed result=" + Arrays.toString(fixed));
        System.out.println();
    }

    public static void runKeyCase() {
        int[] data = {3, 1, 2};
        int[] bad = copyOf(data);
        int[] fixed = copyOf(data);

        System.out.println("case 2: key not saved");
        System.out.println("original=" + Arrays.toString(data));
        badKeyNotSavedInsertionSort(bad);
        fixedKeySavedInsertionSort(fixed);
        System.out.println("bad result=" + Arrays.toString(bad));
        System.out.println("fixed result=" + Arrays.toString(fixed));
        System.out.println();
    }

    public static void main(String[] args) {
        runCase("case 1: inner range error", new int[] {4, 3, 2, 1}, true);
        runKeyCase();
        runCase("case 3: compare direction error", new int[] {30, 10, 20, 50, 40}, false);
    }
}
