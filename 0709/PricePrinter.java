public class PricePrinter {

    public static void printItem(String itemName, int price) {
        System.out.println("Item: " + itemName);
        System.out.println("Price: " + price);
    }

    public static void main(String[] args) {
        printItem("Black tea", 30);
    }
}