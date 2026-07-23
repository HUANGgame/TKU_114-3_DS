import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addName(sc, names);
                        break;

                    case 2:
                        searchName(sc, names);
                        break;

                    case 3:
                        updateName(sc, names);
                        break;

                    case 4:
                        deleteName(sc, names);
                        break;

                    case 5:
                        printAllNames(names);
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println("選項錯誤，請重新輸入");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，請輸入整數選項");
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Name List Manager ===");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部");
        System.out.println("0. 結束");
    }

    public static void addName(
            Scanner sc,
            ArrayList<String> names) {

        System.out.print("請輸入姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("姓名不能是空白");
            return;
        }

        names.add(name);
        System.out.println("新增成功：" + name);
    }

    public static void searchName(
            Scanner sc,
            ArrayList<String> names) {

        System.out.print("請輸入要搜尋的姓名：");
        String keyword = sc.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("搜尋姓名不能是空白");
            return;
        }

        int index = findNameIndex(names, keyword);

        if (index == -1) {
            System.out.println("找不到姓名");
        } else {
            System.out.println(
                    "找到姓名：" + names.get(index)
                            + "，位置：" + index
            );
        }
    }

    public static void updateName(
            Scanner sc,
            ArrayList<String> names) {

        System.out.print("請輸入要修改的姓名：");
        String oldName = sc.nextLine().trim();

        if (oldName.isEmpty()) {
            System.out.println("姓名不能是空白");
            return;
        }

        int index = findNameIndex(names, oldName);

        if (index == -1) {
            System.out.println("找不到姓名");
            return;
        }

        System.out.print("請輸入新的姓名：");
        String newName = sc.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("新姓名不能是空白");
            return;
        }

        names.set(index, newName);
        System.out.println("修改成功：" + newName);
    }

    public static void deleteName(
            Scanner sc,
            ArrayList<String> names) {

        System.out.print("請輸入要刪除的姓名：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("姓名不能是空白");
            return;
        }

        int index = findNameIndex(names, name);

        if (index == -1) {
            System.out.println("刪除失敗，找不到姓名");
        } else {
            String deletedName = names.remove(index);
            System.out.println("刪除成功：" + deletedName);
        }
    }

    public static int findNameIndex(
            ArrayList<String> names,
            String keyword) {

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(keyword.trim())) {
                return i;
            }
        }

        return -1;
    }

    public static void printAllNames(
            ArrayList<String> names) {

        if (names.isEmpty()) {
            System.out.println("目前沒有姓名資料");
            return;
        }

        System.out.println("=== 姓名列表 ===");

        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }
}