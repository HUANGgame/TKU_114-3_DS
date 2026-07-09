public class SubtotalCalculator {

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void main(String[] args) {
        int subtotal = calculateSubtotal(30, 2);
        System.out.println("Subtotal: " + subtotal);
    }
}