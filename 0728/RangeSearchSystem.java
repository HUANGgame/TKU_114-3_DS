import java.util.Arrays;
import java.util.Scanner;

public class RangeSearchSystem {
    static int[] scores = {55, 60, 60, 70, 75, 75, 75, 80, 90, 90, 100};

    public static int firstIndex(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                high = mid - 1;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public static int lastIndex(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                low = mid + 1;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public static int[] rangeOf(int[] values, int target) {
        int first = firstIndex(values, target);
        if (first == -1) {
            return new int[] {-1, -1};
        }
        return new int[] {first, lastIndex(values, target)};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter score: ");
        int target = scanner.nextInt();
        int[] range = rangeOf(scores, target);
        System.out.println("range=" + Arrays.toString(range));
        if (range[0] == -1) {
            System.out.println(target + " not found");
        } else {
            System.out.println("count=" + (range[1] - range[0] + 1));
        }
        scanner.close();
    }
}
