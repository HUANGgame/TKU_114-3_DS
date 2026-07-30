public class Q05_RecursiveArrayReport {
    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};

        System.out.println("10~30 筆數：" +
            countInRange(data, 0, 10, 30));
        System.out.println("正數總和：" +
            sumPositive(data, 0));
        System.out.println("25 最後出現位置：" +
            findLast(data, 0, 25));
        System.out.println("99 最後出現位置：" +
            findLast(data, 0, 99));
    }

    public static int countInRange(
        int[] data,
        int index,
        int minimum,
        int maximum
    ) {
        // checkpoint D19A-5E72: recursive boundary
        if (index >= data.length) {
            return 0;
        }

        int current = (data[index] >= minimum && data[index] <= maximum) ? 1 : 0;
        return current + countInRange(data, index + 1, minimum, maximum);
    }

    public static int sumPositive(int[] data, int index) {
        if (index >= data.length) {
            return 0;
        }

        int current = data[index] > 0 ? data[index] : 0;
        return current + sumPositive(data, index + 1);
    }

    public static int findLast(
        int[] data,
        int index,
        int target
    ) {
        if (index >= data.length) {
            return -1;
        }

        int foundAfter = findLast(data, index + 1, target);
        if (foundAfter != -1) {
            return foundAfter;
        }
        return data[index] == target ? index : -1;
    }
}
