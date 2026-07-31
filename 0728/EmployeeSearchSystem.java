import java.util.Scanner;

public class EmployeeSearchSystem {
    static Employee[] employees = {
        new Employee("E001", "Amy", "Sales", "101"),
        new Employee("E005", "Ben", "IT", "205"),
        new Employee("E009", "Cindy", "HR", "309"),
        new Employee("E012", "David", "IT", "412"),
        new Employee("E020", "Eva", "Finance", "520")
    };

    public static int binarySearch(Employee[] data, String targetId) {
        if (data == null || data.length == 0 || targetId == null) {
            return -1;
        }

        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = data[mid].getId().compareTo(targetId);
            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee id: ");
        String target = scanner.nextLine();

        int index = binarySearch(employees, target);
        if (index == -1) {
            System.out.println(target + " not found");
        } else {
            System.out.println(employees[index]);
        }

        System.out.println("Empty array result=" + binarySearch(new Employee[0], target));
        System.out.println("Duplicate policy: employee id should be unique; first matched index is returned.");
        scanner.close();
    }
}
