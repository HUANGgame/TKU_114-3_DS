public class RecursiveCountdown {
    public static void main(String[] args) {
        countdown(5);
        System.out.println("done");
    }

    public static void countdown(int number) {
        if (number <= 0) {
            return;
        }

        System.out.println(number);
        countdown(number - 1);
    }
}
