import java.util.Arrays;

public class SelectionSortPractice {
    static int comparisons;
    static int swaps;

    public static void selectionSort(int[] values) {
        comparisons = 0;
        swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int selectedIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[selectedIndex]) {
                    selectedIndex = index;
                }
            }

            System.out.println(
                "start=" + start
                    + ", selectedIndex=" + selectedIndex
                    + ", selectedValue=" + values[selectedIndex]
            );

            if (selectedIndex != start) {
                int temp = values[start];
                values[start] = values[selectedIndex];
                values[selectedIndex] = temp;
                swaps++;
            }
            System.out.println(Arrays.toString(values));
        }
    }

    private static void test(int[] values) {
        System.out.println("before=" + Arrays.toString(values));
        selectionSort(values);
        System.out.println("after=" + Arrays.toString(values));
        System.out.println("comparisons=" + comparisons);
        System.out.println("swaps=" + swaps);
        System.out.println();
    }

    public static void main(String[] args) {
        test(new int[] {42, 18, 35, 7, 29, 14});
        test(new int[] {});
        test(new int[] {9});
    }
}
