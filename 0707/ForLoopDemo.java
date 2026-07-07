public class ForLoopDemo {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.println("Round " + i);
            sum += i;
        }

        System.out.println("Sum: " + sum);
    }
}
