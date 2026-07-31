public class RecursiveNameSearchPractice {
    public static int search(String[] names, String target, int index) {
        if (names == null || target == null || index >= names.length) {
            return -1;
        }
        if (names[index].equals(target)) {
            return index;
        }
        return search(names, target, index + 1);
    }

    private static void test(String[] names, String target) {
        System.out.println(target + " index=" + search(names, target, 0));
    }

    public static void main(String[] args) {
        String[] names = {"Amy", "Ben", "Cindy", "David", "Eva"};
        test(new String[0], "Amy");
        test(names, "Amy");
        test(names, "Eva");
        test(names, "Tom");
    }
}
