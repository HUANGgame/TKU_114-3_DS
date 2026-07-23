public class CartItem {
    private String code;
    private String name;
    private int unitPrice;
    private int quantity;

    public static void main(String[] args) {
        ShoppingCartSystem.main(args);
    }

    public CartItem(
            String code,
            String name,
            int unitPrice,
            int quantity) {

        this.code = code.trim();
        this.name = name.trim();
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean addQuantity(int amount) {
        if (amount <= 0) {
            return false;
        }

        quantity += amount;
        return true;
    }

    public boolean setQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        }

        this.quantity = quantity;
        return true;
    }

    public int getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return code
                + " | " + name
                + " | 單價：" + unitPrice
                + " | 數量：" + quantity
                + " | 小計：" + getSubtotal();
    }
}
