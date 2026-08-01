import java.util.ArrayList;

public class LibraryManagementSystem {
    public static void printBooks(String title, ArrayList<Book> books) {
        System.out.println(title);
        if (books.isEmpty()) {
            System.out.println("(empty)");
        }
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();
    }

    public static void printSearchResult(ArrayList<Book> sortedBooks, String id) {
        int index = BookAlgorithms.binarySearchById(sortedBooks, id);
        System.out.println("search id=" + id + ", index=" + index);
        if (index != -1) {
            System.out.println(sortedBooks.get(index));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        printBooks("empty data:", books);
        printSearchResult(books, "B001");

        System.out.println("add B205=" + BookAlgorithms.addBook(books, new Book("B205", "Data Structures", "CS", 18)));
        System.out.println("add B101=" + BookAlgorithms.addBook(books, new Book("B101", "Java Basics", "CS", 32)));
        System.out.println("add B330=" + BookAlgorithms.addBook(books, new Book("B330", "World History", "History", 9)));
        System.out.println("add B150=" + BookAlgorithms.addBook(books, new Book("B150", "Algorithms", "CS", 26)));
        System.out.println("add B275=" + BookAlgorithms.addBook(books, new Book("B275", "Novel A", "Literature", 32)));
        System.out.println("add B420=" + BookAlgorithms.addBook(books, new Book("B420", "Novel B", "Literature", 14)));
        System.out.println("add duplicate B101=" + BookAlgorithms.addBook(books, new Book("B101", "Duplicate", "CS", 99)));
        System.out.println();

        printBooks("main books:", books);

        ArrayList<Book> sortedById = BookAlgorithms.copyBooks(books);
        BookAlgorithms.mergeSortById(sortedById);
        printBooks("sorted by id ascending:", sortedById);
        printSearchResult(sortedById, "B101");
        printSearchResult(sortedById, "B999");

        ArrayList<Book> sortedByBorrow = BookAlgorithms.copyBooks(books);
        BookAlgorithms.mergeSortByBorrowCountDescending(sortedByBorrow);
        printBooks("sorted by borrow count descending:", sortedByBorrow);

        printBooks("category CS:", BookAlgorithms.findAllByCategory(books, "CS"));
        printBooks("category Art:", BookAlgorithms.findAllByCategory(books, "Art"));
    }
}
