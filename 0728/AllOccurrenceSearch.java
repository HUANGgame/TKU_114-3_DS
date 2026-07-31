import java.util.Scanner;

public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] scores = {80, 75, 90, 80, 60, 80, 100, 75, 80};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter score: ");
        int target = scanner.nextInt();

        int count = 0;
        int comparisons = 0;
        System.out.print("Indexes: ");
        for (int index = 0; index < scores.length; index++) {
            comparisons++;
            if (scores[index] == target) {
                System.out.print(index + " ");
                count++;
            }
        }
        System.out.println();

        if (count == 0) {
            System.out.println(target + " not found");
        }
        System.out.println("count=" + count);
        System.out.println("comparisons=" + comparisons);
        scanner.close();
    }
}
