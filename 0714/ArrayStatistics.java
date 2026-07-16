import java.util.Scanner;

public class ArrayStatistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);
        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.println();
        System.out.println("=== All Scores ===");

        for (int i = 0; i < scores.length; i++) {
            System.out.println("Index " + i + ": " + scores[i]);
        }

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;

        System.out.println();
        System.out.println("=== Statistics ===");
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);

        System.out.print("請輸入要搜尋的目標成績：");
        int target = sc.nextInt();

        while (target < 0 || target > 100) {
            System.out.print("成績必須在 0 到 100，請重新輸入：");
            target = sc.nextInt();
        }

        int index = findIndex(scores, target);

        if (index == -1) {
            System.out.println("找不到成績：" + target);
        } else {
            System.out.println("第一次出現的索引：" + index);
        }

        sc.close();
    }

    public static int readCount(Scanner sc) {
        System.out.print("請輸入資料筆數（1～50）：");
        int count = sc.nextInt();

        while (count < 1 || count > 50) {
            System.out.print("資料筆數必須在 1 到 50，請重新輸入：");
            count = sc.nextInt();
        }

        return count;
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            System.out.print("請輸入第 " + (i + 1) + " 筆成績：");
            int score = sc.nextInt();

            while (score < 0 || score > 100) {
                System.out.print("成績必須在 0 到 100，請重新輸入：");
                score = sc.nextInt();
            }

            scores[i] = score;
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];

        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }

        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];

        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }

        return min;
    }

    public static int countPass(int[] scores) {
        int passCount = 0;

        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }

        return passCount;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }

        return -1;
    }
}