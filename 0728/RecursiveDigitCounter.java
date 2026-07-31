public class RecursiveDigitCounter {
    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            return 0;
        }
        if (number == 0) {
            return target == 0 ? 1 : 0;
        }
        return countDigitPositive(number, target);
    }

    private static int countDigitPositive(int number, int target) {
        if (number == 0) {
            return 0;
        }
        int count = number % 10 == target ? 1 : 0;
        return count + countDigitPositive(number / 10, target);
    }

    private static void test(int number) {
        System.out.println("number=" + number);
        for (int target = 0; target <= 9; target++) {
            System.out.println(target + ": " + countDigit(number, target));
        }
    }

    public static void main(String[] args) {
        test(0);
        test(7);
        test(5729);
        test(1002);
        test(55555);
        test(908070);
    }
}
