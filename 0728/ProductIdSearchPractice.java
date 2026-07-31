import java.util.Scanner;

public class ProductIdSearchPractice {
    static String[] productIds = {
        "P205", "P101", "P330", "P150",
        "P808", "P404", "P512", "P099"
    };

    static int comparisons;

    public static int search(String[] ids, String target) {
        comparisons = 0;
        for (int index = 0; index < ids.length; index++) {
            comparisons++;
            if (ids[index].equals(target)) {
                return index;
            }
        }
        return -1;
    }

    public static void printResult(String target) {
        int index = search(productIds, target);
        if (index == -1) {
            System.out.println(target + " not found");
        } else {
            System.out.println(target + " found at index " + index);
        }
        System.out.println("comparisons=" + comparisons);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        String target = scanner.nextLine();
        printResult(target);
        scanner.close();
    }
}
