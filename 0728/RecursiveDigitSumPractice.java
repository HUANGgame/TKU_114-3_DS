public class RecursiveDigitSumPractice {
    public static int digitSum(int number) {
        if (number == 0) {
            return 0;
        }
        return number % 10 + digitSum(number / 10);
    }

    private static void test(int number) {
        System.out.println("digitSum(" + number + ") = " + digitSum(number));
    }

    public static void main(String[] args) {
        test(0);
        test(5);
        test(5729);
        test(1002);
        test(99999);
    }
}
