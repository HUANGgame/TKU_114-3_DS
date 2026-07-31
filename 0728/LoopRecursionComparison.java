public class LoopRecursionComparison {
    public static void main(String[] args) {
        int number = 6;

        System.out.println("Loop sum=" + sumByLoop(number));
        System.out.println("Recursion sum=" + sumByRecursion(number));
    }

    public static int sumByLoop(int number) {
        int total = 0;
        for (int value = 1; value <= number; value++) {
            total += value;
        }
        return total;
    }

    public static int sumByRecursion(int number) {
        if (number <= 0) {
            return 0;
        }
        return number + sumByRecursion(number - 1);
    }
}
