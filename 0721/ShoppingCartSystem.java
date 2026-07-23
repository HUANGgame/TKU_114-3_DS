import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addItem(sc, cart);
                        break;

                    case 2:
                        updateQuantity(sc, cart);
                        break;

                    case 3:
                        removeItem(sc, cart);
                        break;

                    case 4:
                        printCart(cart);
                        break;

                    case 5:
                        System.out.println(
                                "購物車總額：" + calculateTotal(cart)
                        );
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println("選項錯誤，請重新輸入");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，請輸入整數");
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Shopping Cart ===");
        System.out.println("1. 加入商品");
        System.out.println("2. 修改數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 顯示購物車");
        System.out.println("5. 計算總額");
        System.out.println("0. 結束");
    }

    public static void addItem(
            Scanner sc,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入商品代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("商品代碼不能是空白");
            return;
        }

        System.out.print("請輸入數量：");
        int quantity = readInteger(sc);

        if (quantity <= 0) {
            System.out.println("加入失敗，數量必須大於 0");
            return;
        }

        CartItem existingItem = findByCode(cart, code);

        if (existingItem != null) {
            existingItem.addQuantity(quantity);

            System.out.println(
                    "商品已存在，數量增加為："
                            + existingItem.getQuantity()
            );
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("商品名稱不能是空白");
            return;
        }

        System.out.print("請輸入商品單價：");
        int unitPrice = readInteger(sc);

        if (unitPrice <= 0) {
            System.out.println("加入失敗，單價必須大於 0");
            return;
        }

        CartItem item = new CartItem(
                code,
                name,
                unitPrice,
                quantity
        );

        cart.add(item);
        System.out.println("商品加入成功：" + item);
    }

    public static void updateQuantity(
            Scanner sc,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入商品代碼：");
        String code = sc.nextLine().trim();

        CartItem item = findByCode(cart, code);

        if (item == null) {
            System.out.println("修改失敗，找不到商品");
            return;
        }

        System.out.print("請輸入新的數量：");
        int newQuantity = readInteger(sc);

        if (item.setQuantity(newQuantity)) {
            System.out.println(
                    "修改成功，目前數量：" + item.getQuantity()
            );
        } else {
            System.out.println("修改失敗，數量必須大於 0");
        }
    }

    public static void removeItem(
            Scanner sc,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入要移除的商品代碼：");
        String code = sc.nextLine().trim();

        CartItem item = findByCode(cart, code);

        if (item == null) {
            System.out.println("移除失敗，找不到商品");
            return;
        }

        cart.remove(item);
        System.out.println("移除成功：" + item.getName());
    }

    public static CartItem findByCode(
            ArrayList<CartItem> cart,
            String code) {

        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code.trim())) {
                return item;
            }
        }

        return null;
    }

    public static int calculateTotal(
            ArrayList<CartItem> cart) {

        int total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        return total;
    }

    public static void printCart(
            ArrayList<CartItem> cart) {

        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的");
            return;
        }

        System.out.println("=== 購物車內容 ===");

        for (CartItem item : cart) {
            System.out.println(item);
        }

        System.out.println("總額：" + calculateTotal(cart));
    }

    public static int readInteger(Scanner sc) {
        while (true) {
            try {
                int number = sc.nextInt();
                sc.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.print("輸入錯誤，請輸入整數：");
                sc.nextLine();
            }
        }
    }
}