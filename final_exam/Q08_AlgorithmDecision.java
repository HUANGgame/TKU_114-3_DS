public class Q08_AlgorithmDecision {
    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }

        System.out.println("是否升冪：" +
            isSortedAscending(data));
        System.out.println("循序搜尋比較次數：" +
            sequentialChecks(data, 192));
        System.out.println("二分搜尋比較次數：" +
            binaryChecks(data, 192));
        System.out.println("建議：" +
            chooseSearch(true, data.length, 5));
    }

    public static boolean isSortedAscending(int[] data) {
        for (int index = 1; index < data.length; index++) {
            if (data[index - 1] > data[index]) {
                return false;
            }
        }
        return true;
    }

    public static int sequentialChecks(int[] data, int target) {
        int checks = 0;
        for (int value : data) {
            checks++;
            if (value == target) {
                return checks;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        int checks = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            checks++;

            if (data[mid] == target) {
                return checks;
            }
            if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return checks;
    }

    public static String chooseSearch(
        boolean sorted,
        int dataSize,
        int expectedSearches
    ) {
        if (!sorted) {
            return "SEQUENTIAL";
        }
        if (dataSize >= 32 && expectedSearches >= 2) {
            return "BINARY";
        }
        return "SEQUENTIAL";
    }
}
