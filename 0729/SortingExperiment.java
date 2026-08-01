import java.util.Arrays;

public class SortingExperiment {
    static class Stats {
        int comparisons;
        int swaps;
        int moves;
    }

    public static int[] copyOf(int[] source) {
        int[] copy = new int[source.length];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index];
        }
        return copy;
    }

    public static Stats selectionSort(int[] values) {
        Stats stats = new Stats();

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                stats.comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                stats.swaps++;
            }
        }
        return stats;
    }

    public static Stats insertionSort(int[] values) {
        Stats stats = new Stats();

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            stats.moves++;

            while (position >= 0) {
                stats.comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                stats.moves++;
                position--;
            }

            values[position + 1] = key;
            stats.moves++;
        }
        return stats;
    }

    public static void printStats(String algorithm, Stats stats, int[] result) {
        System.out.println(
            algorithm
                + ": comparisons=" + stats.comparisons
                + ", swaps=" + stats.swaps
                + ", moves=" + stats.moves
                + ", result=" + Arrays.toString(result)
        );
    }

    public static void runExperiment(String label, int[] original) {
        int[] selectionInput = copyOf(original);
        int[] insertionInput = copyOf(original);

        System.out.println(label);
        System.out.println("original=" + Arrays.toString(original));

        Stats selectionStats = selectionSort(selectionInput);
        Stats insertionStats = insertionSort(insertionInput);

        printStats("selection sort", selectionStats, selectionInput);
        printStats("insertion sort", insertionStats, insertionInput);
        printObservation(label, selectionStats, insertionStats);
        System.out.println();
    }

    public static void printObservation(String label, Stats selection, Stats insertion) {
        if (label.equals("sorted data")) {
            System.out.println("Observation: insertion sort is best here because it has few comparisons and no shifting.");
        } else if (label.equals("reverse data")) {
            System.out.println("Observation: insertion sort has the most moves here because every key moves to the front.");
        } else {
            System.out.println("Observation: random data is between sorted and reverse data; selection sort comparisons stay fixed.");
        }
        System.out.println(
            "Summary: selection comparisons=" + selection.comparisons
                + ", insertion comparisons=" + insertion.comparisons
        );
    }

    public static void main(String[] args) {
        int[] sorted = {5, 10, 15, 20, 25, 30, 35, 40};
        int[] reverse = {40, 35, 30, 25, 20, 15, 10, 5};
        int[] random = {25, 5, 35, 10, 40, 20, 15, 30};

        runExperiment("sorted data", sorted);
        runExperiment("reverse data", reverse);
        runExperiment("random data", random);
    }
}
