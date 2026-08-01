public class ProductSortingSystem {
    static final int PRICE_ASC = 1;
    static final int PRICE_DESC = 2;
    static final int STOCK_DESC = 3;

    public static StoreProduct[] copyProducts(StoreProduct[] source) {
        StoreProduct[] copy = new StoreProduct[source.length];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index];
        }
        return copy;
    }

    public static boolean shouldComeBefore(StoreProduct a, StoreProduct b, int mode) {
        if (mode == PRICE_ASC) {
            return a.getPrice() < b.getPrice();
        }
        if (mode == PRICE_DESC) {
            return a.getPrice() > b.getPrice();
        }
        if (mode == STOCK_DESC) {
            return a.getStock() > b.getStock();
        }
        return false;
    }

    public static void insertionSort(StoreProduct[] products, int mode) {
        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            int position = index - 1;

            while (position >= 0 && shouldComeBefore(key, products[position], mode)) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }

    public static void runMode(StoreProduct[] original, int mode, String direction) {
        StoreProduct[] products = copyProducts(original);
        insertionSort(products, mode);

        System.out.println(direction);
        for (int index = 0; index < products.length; index++) {
            System.out.println("position=" + (index + 1) + ", " + products[index]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StoreProduct[] products = {
            new StoreProduct("S001", "Keyboard", 1200, 8),
            new StoreProduct("S002", "Mouse", 650, 20),
            new StoreProduct("S003", "Monitor", 5200, 5),
            new StoreProduct("S004", "USB Cable", 250, 40),
            new StoreProduct("S005", "Webcam", 1800, 7),
            new StoreProduct("S006", "Speaker", 1500, 12),
            new StoreProduct("S007", "Mouse Pad", 150, 35),
            new StoreProduct("S008", "SSD", 2400, 9),
            new StoreProduct("S009", "RAM", 1600, 15),
            new StoreProduct("S010", "HDMI Cable", 250, 18)
        };

        runMode(products, PRICE_ASC, "Sort: price ascending");
        runMode(products, PRICE_DESC, "Sort: price descending");
        runMode(products, STOCK_DESC, "Sort: stock descending");
    }
}
