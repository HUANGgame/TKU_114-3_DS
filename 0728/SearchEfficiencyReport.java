public class SearchEfficiencyReport {
    static int comparisons;

    public static int sequentialSearch(int[] values, int target) {
        comparisons = 0;
        for (int index = 0; index < values.length; index++) {
            comparisons++;
            if (values[index] == target) {
                return index;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] values, int target) {
        comparisons = 0;
        int low = 0;
        int high = values.length - 1;
        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                return mid;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int[] buildData(int size) {
        int[] data = new int[size];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 2;
        }
        return data;
    }

    private static void report(int size) {
        int[] data = buildData(size);
        int[] targets = {data[0], data[data.length - 1], -1};
        String[] labels = {"first", "last", "missing"};

        System.out.println("size=" + size);
        for (int i = 0; i < targets.length; i++) {
            sequentialSearch(data, targets[i]);
            int seq = comparisons;
            binarySearch(data, targets[i]);
            int bin = comparisons;
            System.out.println(labels[i] + ": sequential=" + seq + ", binary=" + bin);
        }
    }

    public static void main(String[] args) {
        report(16);
        report(128);
        report(1024);
        System.out.println("Observation: sequential search grows close to O(n), binary search grows close to O(log n) on sorted data.");
    }
}
