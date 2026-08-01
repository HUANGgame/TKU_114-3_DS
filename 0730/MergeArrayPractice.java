import java.util.Arrays;

public class MergeArrayPractice {
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex] = left[leftIndex];
                leftIndex++;
            } else {
                result[resultIndex] = right[rightIndex];
                rightIndex++;
            }
            resultIndex++;
        }

        while (leftIndex < left.length) {
            result[resultIndex] = left[leftIndex];
            leftIndex++;
            resultIndex++;
        }

        while (rightIndex < right.length) {
            result[resultIndex] = right[rightIndex];
            rightIndex++;
            resultIndex++;
        }

        return result;
    }

    private static void test(String label, int[] left, int[] right) {
        int[] merged = merge(left, right);
        System.out.println(label);
        System.out.println("left=" + Arrays.toString(left));
        System.out.println("right=" + Arrays.toString(right));
        System.out.println("merged=" + Arrays.toString(merged));
        System.out.println("length=" + merged.length);
        System.out.println();
    }

    public static void main(String[] args) {
        test("different lengths", new int[] {1, 4, 9}, new int[] {2, 3, 7, 10, 15});
        test("left empty", new int[] {}, new int[] {-3, 0, 8});
        test("right empty", new int[] {-5, -1, 2}, new int[] {});
        test("duplicates", new int[] {1, 2, 2, 6}, new int[] {2, 2, 5});
        test("negative values", new int[] {-10, -4, 0, 9}, new int[] {-8, -4, 3});
    }
}
