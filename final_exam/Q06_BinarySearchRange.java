public class Q06_BinarySearchRange {
    public static void main(String[] args) {
        int[] data = {5, 10, 10, 10, 18, 25, 25, 40};

        System.out.println("10 第一個位置：" + findFirst(data, 10));
        System.out.println("10 最後一個位置：" + findLast(data, 10));
        System.out.println("10 出現次數：" +
            countOccurrences(data, 10));
        System.out.println("99 第一個位置：" + findFirst(data, 99));
    }

    public static int findFirst(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] >= target) {
                if (data[mid] == target) {
                    result = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static int findLast(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] <= target) {
                if (data[mid] == target) {
                    result = mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static int countOccurrences(int[] data, int target) {
        int first = findFirst(data, target);
        if (first == -1) {
            return 0;
        }
        return findLast(data, target) - first + 1;
    }
}
