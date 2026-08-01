import java.util.Arrays;

public class InsertionSortPractice {
    static int comparisons;
    static int shifts;

    public static void insertionSort(int[] values) {
        comparisons = 0;
        shifts = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                shifts++;
                position--;
            }

            int insertPosition = position + 1;
            values[insertPosition] = key;
            System.out.println(
                "key=" + key
                    + ", insertPosition=" + insertPosition
                    + ", array=" + Arrays.toString(values)
            );
        }
    }

    private static int test(String label, int[] values) {
        System.out.println(label);
        System.out.println("before=" + Arrays.toString(values));
        insertionSort(values);
        System.out.println("after=" + Arrays.toString(values));
        System.out.println("comparisons=" + comparisons);
        System.out.println("shifts=" + shifts);
        System.out.println();
        return shifts;
    }

    public static void main(String[] args) {
        int normalShifts = test("normal data", new int[] {30, 10, 20, 50, 40, 5});
        int sortedShifts = test("sorted data", new int[] {5, 10, 20, 30, 40, 50});
        int reverseShifts = test("reverse data", new int[] {50, 40, 30, 20, 10, 5});

        String most = "normal data";
        int maxShifts = normalShifts;
        if (sortedShifts > maxShifts) {
            most = "sorted data";
            maxShifts = sortedShifts;
        }
        if (reverseShifts > maxShifts) {
            most = "reverse data";
            maxShifts = reverseShifts;
        }

        System.out.println("Most shifts: " + most + " (" + maxShifts + ")");
    }
}
