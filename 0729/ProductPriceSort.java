public class ProductPriceSort {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P103", "Keyboard", 1290, 5),
            new Product("P205", "Mouse", 650, 8),
            new Product("P118", "Monitor", 5200, 2),
            new Product("P310", "Webcam", 1290, 3)
        };

        insertionSortByPrice(products);

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            int position = index - 1;

            while (position >= 0 &&
                   products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }
}
