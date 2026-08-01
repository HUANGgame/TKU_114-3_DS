public class ProductSortPractice {
    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            int position = index - 1;

            while (position >= 0 && products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }

    private static void printProducts(Product[] products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P205", "Wireless Mouse", 650, 4),
            new Product("P101", "Keyboard", 1200, 8),
            new Product("P330", "Gaming Mouse", 1800, 2),
            new Product("P150", "Monitor", 5200, 5),
            new Product("P220", "USB Cable", 250, 30),
            new Product("P221", "HDMI Cable", 250, 12),
            new Product("P410", "Webcam", 1200, 6),
            new Product("P099", "Mouse Pad", 150, 20)
        };

        insertionSortByPrice(products);
        printProducts(products);
    }
}
