import java.util.ArrayList;

public class BookAlgorithms {
    public static ArrayList<Book> copyBooks(ArrayList<Book> books) {
        ArrayList<Book> copy = new ArrayList<>();
        for (Book book : books) {
            copy.add(book);
        }
        return copy;
    }

    public static boolean containsId(ArrayList<Book> books, String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addBook(ArrayList<Book> books, Book book) {
        if (book == null || containsId(books, book.getId())) {
            return false;
        }
        books.add(book);
        return true;
    }

    public static void mergeSortById(ArrayList<Book> books) {
        if (books.size() <= 1) {
            return;
        }
        ArrayList<Book> temp = copyBooks(books);
        mergeSortById(books, temp, 0, books.size() - 1);
    }

    private static void mergeSortById(ArrayList<Book> books, ArrayList<Book> temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(books, temp, left, mid);
        mergeSortById(books, temp, mid + 1, right);
        mergeById(books, temp, left, mid, right);
    }

    private static void mergeById(ArrayList<Book> books, ArrayList<Book> temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (books.get(leftIndex).getId().compareTo(books.get(rightIndex).getId()) <= 0) {
                temp.set(output, books.get(leftIndex));
                leftIndex++;
            } else {
                temp.set(output, books.get(rightIndex));
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp.set(output, books.get(leftIndex));
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp.set(output, books.get(rightIndex));
            rightIndex++;
            output++;
        }

        for (int index = left; index <= right; index++) {
            books.set(index, temp.get(index));
        }
    }

    public static void mergeSortByBorrowCountDescending(ArrayList<Book> books) {
        if (books.size() <= 1) {
            return;
        }
        ArrayList<Book> temp = copyBooks(books);
        mergeSortByBorrowCountDescending(books, temp, 0, books.size() - 1);
    }

    private static void mergeSortByBorrowCountDescending(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDescending(books, temp, left, mid);
        mergeSortByBorrowCountDescending(books, temp, mid + 1, right);
        mergeByBorrowCountDescending(books, temp, left, mid, right);
    }

    private static void mergeByBorrowCountDescending(
        ArrayList<Book> books,
        ArrayList<Book> temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (books.get(leftIndex).getBorrowCount() >= books.get(rightIndex).getBorrowCount()) {
                temp.set(output, books.get(leftIndex));
                leftIndex++;
            } else {
                temp.set(output, books.get(rightIndex));
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp.set(output, books.get(leftIndex));
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp.set(output, books.get(rightIndex));
            rightIndex++;
            output++;
        }

        for (int index = left; index <= right; index++) {
            books.set(index, temp.get(index));
        }
    }

    public static int binarySearchById(ArrayList<Book> sortedBooks, String id) {
        int low = 0;
        int high = sortedBooks.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedBooks.get(mid).getId().compareTo(id);

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

    public static ArrayList<Book> findAllByCategory(ArrayList<Book> books, String category) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                results.add(book);
            }
        }
        return results;
    }
}
