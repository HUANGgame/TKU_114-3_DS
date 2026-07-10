public class PriceMaxMin {
    public static void main(String[] args) {
        int price1 = 30;
        int price2 = 45;
        int price3 = 60;

        int maxPrice = price1;
        int minPrice = price1;

        if (price2 > maxPrice) {
            maxPrice = price2;
        }

        if (price3 > maxPrice) {
            maxPrice = price3;
        }

        if (price2 < minPrice) {
            minPrice = price2;
        }

        if (price3 < minPrice) {
            minPrice = price3;
        }

        System.out.println("Max price: " + maxPrice);
        System.out.println("Min price: " + minPrice);
    }
}