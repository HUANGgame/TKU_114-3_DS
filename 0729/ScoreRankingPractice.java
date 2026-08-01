public class ScoreRankingPractice {
    public static void selectionSortDescending(int[] scores) {
        for (int start = 0; start < scores.length - 1; start++) {
            int maxIndex = start;
            for (int index = start + 1; index < scores.length; index++) {
                if (scores[index] > scores[maxIndex]) {
                    maxIndex = index;
                }
            }

            if (maxIndex != start) {
                int temp = scores[start];
                scores[start] = scores[maxIndex];
                scores[maxIndex] = temp;
            }
        }
    }

    public static void printRanking(int[] scores) {
        int rank = 0;
        int previousScore = -1;

        for (int index = 0; index < scores.length; index++) {
            if (index == 0 || scores[index] != previousScore) {
                rank = index + 1;
                previousScore = scores[index];
            }

            String result = scores[index] >= 60 ? "pass" : "fail";
            System.out.println("rank=" + rank + ", score=" + scores[index] + ", " + result);
        }
    }

    public static void main(String[] args) {
        int[] scores = {88, 95, 72, 95, 60, 59, 88, 100, 72};

        selectionSortDescending(scores);
        printRanking(scores);
    }
}
