public class TransactionSortingSystem {
    public static boolean shouldComeBefore(Transaction a, Transaction b) {
        if (a.getAmount() != b.getAmount()) {
            return a.getAmount() > b.getAmount();
        }
        return a.getTimeOrder() < b.getTimeOrder();
    }

    public static void insertionSort(Transaction[] transactions) {
        for (int index = 1; index < transactions.length; index++) {
            Transaction key = transactions[index];
            int position = index - 1;

            while (position >= 0 && shouldComeBefore(key, transactions[position])) {
                transactions[position + 1] = transactions[position];
                position--;
            }
            transactions[position + 1] = key;
        }
    }

    public static void printTransactions(Transaction[] transactions) {
        for (int index = 0; index < transactions.length; index++) {
            System.out.println("rank=" + (index + 1) + ", " + transactions[index]);
        }
    }

    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("T006", "A-102", 5000, 6),
            new Transaction("T001", "A-100", 1200, 1),
            new Transaction("T004", "A-101", 5000, 4),
            new Transaction("T002", "A-103", 800, 2),
            new Transaction("T008", "A-105", 1200, 8),
            new Transaction("T003", "A-104", 3000, 3),
            new Transaction("T005", "A-106", 5000, 5),
            new Transaction("T007", "A-107", 3000, 7)
        };

        insertionSort(transactions);
        System.out.println("Sorted by amount descending, then timeOrder ascending:");
        printTransactions(transactions);
    }
}
