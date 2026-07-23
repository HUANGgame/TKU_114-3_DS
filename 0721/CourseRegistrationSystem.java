import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        courses.add(new Course("C001", "Java Programming", 3));
        courses.add(new Course("C002", "Data Structures", 2));
        courses.add(new Course("C003", "Database Systems", 4));

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addCourse(sc, courses);
                        break;

                    case 2:
                        registerCourse(sc, courses);
                        break;

                    case 3:
                        withdrawCourse(sc, courses);
                        break;

                    case 4:
                        deleteCourse(sc, courses);
                        break;

                    case 5:
                        searchCourse(sc, courses);
                        break;

                    case 6:
                        printAllCourses(courses);
                        break;

                    case 7:
                        printSummary(courses);
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
        System.out.println("=== 課程選課系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 選課");
        System.out.println("3. 退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示全部課程");
        System.out.println("7. 顯示統計資料");
        System.out.println("0. 結束");
    }

    public static void addCourse(
            Scanner sc,
            ArrayList<Course> courses) {

        System.out.print("請輸入課程代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗，課程代碼不能是空白");
            return;
        }

        if (findByCode(courses, code) != null) {
            System.out.println("新增失敗，課程代碼已存在");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，課程名稱不能是空白");
            return;
        }

        System.out.print("請輸入課程容量：");
        int capacity = readPositiveInteger(sc);

        Course course = new Course(code, name, capacity);
        courses.add(course);

        System.out.println("新增成功：" + course);
    }

    public static void registerCourse(
            Scanner sc,
            ArrayList<Course> courses) {

        String code = readCourseCode(sc);
        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("選課失敗，找不到課程");
            return;
        }

        if (course.register()) {
            System.out.println("選課成功：" + course.getName());
            System.out.println(
                    "目前人數："
                            + course.getCurrentStudents()
                            + "/"
                            + course.getCapacity()
            );
        } else {
            System.out.println("選課失敗，課程已額滿");
        }
    }

    public static void withdrawCourse(
            Scanner sc,
            ArrayList<Course> courses) {

        String code = readCourseCode(sc);
        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("退選失敗，找不到課程");
            return;
        }

        if (course.withdraw()) {
            System.out.println("退選成功：" + course.getName());
            System.out.println(
                    "目前人數："
                            + course.getCurrentStudents()
                            + "/"
                            + course.getCapacity()
            );
        } else {
            System.out.println("退選失敗，目前選課人數為 0");
        }
    }

    public static void deleteCourse(
            Scanner sc,
            ArrayList<Course> courses) {

        String code = readCourseCode(sc);
        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("刪除失敗，找不到課程");
            return;
        }

        courses.remove(course);
        System.out.println("刪除成功：" + course.getName());
    }

    public static void searchCourse(
            Scanner sc,
            ArrayList<Course> courses) {

        String code = readCourseCode(sc);
        Course course = findByCode(courses, code);

        if (course == null) {
            System.out.println("找不到課程");
        } else {
            System.out.println("搜尋結果：" + course);
        }
    }

    public static Course findByCode(
            ArrayList<Course> courses,
            String code) {

        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code.trim())) {
                return course;
            }
        }

        return null;
    }

    public static String readCourseCode(Scanner sc) {
        System.out.print("請輸入課程代碼：");
        return sc.nextLine().trim();
    }

    public static void printAllCourses(
            ArrayList<Course> courses) {

        if (courses.isEmpty()) {
            System.out.println("目前沒有課程資料");
            return;
        }

        System.out.println("=== 全部課程 ===");

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static int calculateTotalRegistrations(
            ArrayList<Course> courses) {

        int total = 0;

        for (Course course : courses) {
            total += course.getCurrentStudents();
        }

        return total;
    }

    public static void printFullCourses(
            ArrayList<Course> courses) {

        boolean found = false;

        System.out.println("額滿課程：");

        for (Course course : courses) {
            if (course.isFull()) {
                System.out.println(course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有額滿課程");
        }
    }

    public static void printSummary(
            ArrayList<Course> courses) {

        System.out.println("=== 課程統計 ===");
        System.out.println("總課程數：" + courses.size());
        System.out.println(
                "總選課人次："
                        + calculateTotalRegistrations(courses)
        );

        printFullCourses(courses);
    }

    public static int readPositiveInteger(Scanner sc) {
        while (true) {
            try {
                int number = sc.nextInt();
                sc.nextLine();

                if (number > 0) {
                    return number;
                }

                System.out.print("數值必須大於 0，請重新輸入：");
            } catch (InputMismatchException e) {
                System.out.print("輸入錯誤，請輸入整數：");
                sc.nextLine();
            }
        }
    }
}