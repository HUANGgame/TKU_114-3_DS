import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipment> equipmentList = new ArrayList<>();

        equipmentList.add(new Equipment("E001", "Laptop"));
        equipmentList.add(new Equipment("E002", "Projector"));
        equipmentList.add(new Equipment("E003", "Camera"));

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addEquipment(sc, equipmentList);
                        break;

                    case 2:
                        searchEquipment(sc, equipmentList);
                        break;

                    case 3:
                        borrowEquipment(sc, equipmentList);
                        break;

                    case 4:
                        returnEquipment(sc, equipmentList);
                        break;

                    case 5:
                        printAvailableEquipment(equipmentList);
                        break;

                    case 6:
                        printAllEquipment(equipmentList);
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
        System.out.println("=== Equipment Manager ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出可借設備");
        System.out.println("6. 列出全部設備");
        System.out.println("0. 結束");
    }

    public static void addEquipment(
            Scanner sc,
            ArrayList<Equipment> equipmentList) {

        System.out.print("請輸入設備代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("設備代碼不能是空白");
            return;
        }

        if (findByCode(equipmentList, code) != null) {
            System.out.println("新增失敗，設備代碼已存在");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("設備名稱不能是空白");
            return;
        }

        Equipment equipment = new Equipment(code, name);
        equipmentList.add(equipment);

        System.out.println("新增成功：" + equipment);
    }

    public static void searchEquipment(
            Scanner sc,
            ArrayList<Equipment> equipmentList) {

        String code = readCode(sc);
        Equipment equipment = findByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("找不到設備");
        } else {
            System.out.println("搜尋結果：" + equipment);
        }
    }

    public static void borrowEquipment(
            Scanner sc,
            ArrayList<Equipment> equipmentList) {

        String code = readCode(sc);
        Equipment equipment = findByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("借出失敗，找不到設備");
            return;
        }

        if (equipment.borrowEquipment()) {
            System.out.println("借出成功：" + equipment.getName());
        } else {
            System.out.println("借出失敗，設備已被借出");
        }
    }

    public static void returnEquipment(
            Scanner sc,
            ArrayList<Equipment> equipmentList) {

        String code = readCode(sc);
        Equipment equipment = findByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("歸還失敗，找不到設備");
            return;
        }

        if (equipment.returnEquipment()) {
            System.out.println("歸還成功：" + equipment.getName());
        } else {
            System.out.println("歸還失敗，設備目前沒有借出");
        }
    }

    public static String readCode(Scanner sc) {
        System.out.print("請輸入設備代碼：");
        return sc.nextLine().trim();
    }

    public static Equipment findByCode(
            ArrayList<Equipment> equipmentList,
            String code) {

        for (Equipment equipment : equipmentList) {
            if (equipment.getCode().equalsIgnoreCase(code.trim())) {
                return equipment;
            }
        }

        return null;
    }

    public static void printAvailableEquipment(
            ArrayList<Equipment> equipmentList) {

        boolean found = false;

        System.out.println("=== 可借設備 ===");

        for (Equipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                System.out.println(equipment);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有可借設備");
        }
    }

    public static void printAllEquipment(
            ArrayList<Equipment> equipmentList) {

        if (equipmentList.isEmpty()) {
            System.out.println("目前沒有設備資料");
            return;
        }

        System.out.println("=== 全部設備 ===");

        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }
}