import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(
                "C001",
                "Amy",
                "0912345678",
                "amy@gmail.com"
        ));

        contacts.add(new Contact(
                "C002",
                "Ben",
                "0922333444",
                "ben@gmail.com"
        ));

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addContact(sc, contacts);
                        break;

                    case 2:
                        searchContact(sc, contacts);
                        break;

                    case 3:
                        updatePhone(sc, contacts);
                        break;

                    case 4:
                        deleteContact(sc, contacts);
                        break;

                    case 5:
                        printAllContacts(contacts);
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println("選項錯誤，請重新輸入");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，選項必須是整數");
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整清單");
        System.out.println("0. 結束");
    }

    public static void addContact(
            Scanner sc,
            ArrayList<Contact> contacts) {

        System.out.print("請輸入聯絡人代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("代碼不能是空白");
            return;
        }

        if (findByCode(contacts, code) != null) {
            System.out.println("新增失敗，代碼已存在");
            return;
        }

        System.out.print("請輸入姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，姓名不能是空白");
            return;
        }

        System.out.print("請輸入電話：");
        String phone = sc.nextLine().trim();

        if (phone.isEmpty()) {
            System.out.println("新增失敗，電話不能是空白");
            return;
        }

        System.out.print("請輸入電子郵件：");
        String email = sc.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("新增失敗，電子郵件不能是空白");
            return;
        }

        Contact contact = new Contact(code, name, phone, email);
        contacts.add(contact);

        System.out.println("新增成功：" + contact);
    }

    public static void searchContact(
            Scanner sc,
            ArrayList<Contact> contacts) {

        System.out.print("請輸入聯絡人代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("代碼不能是空白");
            return;
        }

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("找不到聯絡人");
        } else {
            System.out.println("搜尋結果：" + contact);
        }
    }

    public static void updatePhone(
            Scanner sc,
            ArrayList<Contact> contacts) {

        System.out.print("請輸入聯絡人代碼：");
        String code = sc.nextLine().trim();

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("修改失敗，找不到聯絡人");
            return;
        }

        System.out.print("請輸入新電話：");
        String newPhone = sc.nextLine();

        if (contact.setPhone(newPhone)) {
            System.out.println("電話修改成功");
            System.out.println(contact);
        } else {
            System.out.println("修改失敗，電話不能是空白");
        }
    }

    public static void deleteContact(
            Scanner sc,
            ArrayList<Contact> contacts) {

        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = sc.nextLine().trim();

        Contact contact = findByCode(contacts, code);

        if (contact == null) {
            System.out.println("刪除失敗，找不到聯絡人");
            return;
        }

        contacts.remove(contact);
        System.out.println("刪除成功：" + contact.getName());
    }

    public static Contact findByCode(
            ArrayList<Contact> contacts,
            String code) {

        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(code.trim())) {
                return contact;
            }
        }

        return null;
    }

    public static void printAllContacts(
            ArrayList<Contact> contacts) {

        if (contacts.isEmpty()) {
            System.out.println("目前沒有聯絡人資料");
            return;
        }

        System.out.println("=== 聯絡人清單 ===");

        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}