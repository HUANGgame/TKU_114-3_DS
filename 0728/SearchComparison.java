public class SearchComparison {
    public static void main(String[] args) {
        int[] values = new int[1024];
        for (int index = 0; index < values.length; index++) {
            values[index] = (index + 1) * 2;
        }

        int target = 2048;
        System.out.println("Sequential Search comparisons=" + sequentialChecks(values, target));
        System.out.println("Binary Search comparisons=" + binaryChecks(values, target));
    }

    public static int sequentialChecks(int[] values, int target) {
        int checks = 0;
        for (int value : values) {
            checks++;
            if (value == target) {
                break;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int checks = 0;

        while (low <= high) {
            checks++;
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                break;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return checks;
    }
}
