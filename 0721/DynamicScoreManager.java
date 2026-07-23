import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        readScores(sc, scores);

        if (scores.isEmpty()) {
            System.out.println("沒有輸入任何成績");
        } else {
            printSummary(scores);
        }

        sc.close();
    }

    public static void readScores(
            Scanner sc,
            ArrayList<Integer> scores) {

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");

            try {
                int score = sc.nextInt();

                if (score == -1) {
                    break;
                }

                if (isValidScore(score)) {
                    scores.add(score);
                } else {
                    System.out.println("成績必須介於 0 到 100");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，請輸入整數");
                sc.nextLine();
            }
        }
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public static double calculateAverage(
            ArrayList<Integer> scores) {

        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return (double) total / scores.size();
    }

    public static int findHighest(
            ArrayList<Integer> scores) {

        int highest = scores.get(0);

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
        }

        return highest;
    }

    public static int findLowest(
            ArrayList<Integer> scores) {

        int lowest = scores.get(0);

        for (int score : scores) {
            if (score < lowest) {
                lowest = score;
            }
        }

        return lowest;
    }

    public static ArrayList<Integer> getPassingScores(
            ArrayList<Integer> scores) {

        ArrayList<Integer> passingScores = new ArrayList<>();

        for (int score : scores) {
            if (score >= 60) {
                passingScores.add(score);
            }
        }

        return passingScores;
    }

    public static void printSummary(
            ArrayList<Integer> scores) {

        ArrayList<Integer> passingScores = getPassingScores(scores);

        System.out.println();
        System.out.println("=== 成績統計 ===");
        System.out.println("成績筆數：" + scores.size());
        System.out.printf(
                "平均成績：%.2f%n",
                calculateAverage(scores)
        );
        System.out.println("最高成績：" + findHighest(scores));
        System.out.println("最低成績：" + findLowest(scores));
        System.out.println("及格名單：" + passingScores);
    }
}