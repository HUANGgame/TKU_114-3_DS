public class RecursionTrace {
    public static void main(String[] args) {
        trace(3);
    }

    public static void trace(int level) {
        if (level == 0) {
            System.out.println("base case");
            return;
        }

        System.out.println("enter " + level);
        trace(level - 1);
        System.out.println("return " + level);
    }
}
