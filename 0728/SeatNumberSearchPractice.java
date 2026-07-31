import java.util.Scanner;

public class SeatNumberSearchPractice {
    static int[] seats = {
        101, 105, 108, 112, 118, 120,
        125, 130, 136, 145, 150, 160
    };

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("low=" + low + ", mid=" + mid + ", high=" + high);

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter seat number: ");
        int target = scanner.nextInt();
        int index = binarySearch(seats, target);
        if (index == -1) {
            System.out.println(target + " not found");
        } else {
            System.out.println(target + " found at index " + index);
        }
        scanner.close();
    }
}
