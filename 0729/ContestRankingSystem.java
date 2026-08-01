public class ContestRankingSystem {
    public static boolean shouldComeBefore(Contestant a, Contestant b) {
        if (a.getScore() != b.getScore()) {
            return a.getScore() > b.getScore();
        }
        return a.getSeconds() < b.getSeconds();
    }

    public static boolean sameResult(Contestant a, Contestant b) {
        return a.getScore() == b.getScore() && a.getSeconds() == b.getSeconds();
    }

    public static void insertionSort(Contestant[] contestants) {
        for (int index = 1; index < contestants.length; index++) {
            Contestant key = contestants[index];
            int position = index - 1;

            while (position >= 0 && shouldComeBefore(key, contestants[position])) {
                contestants[position + 1] = contestants[position];
                position--;
            }
            contestants[position + 1] = key;
        }
    }

    public static void printRanking(Contestant[] contestants) {
        int rank = 0;
        Contestant previous = null;

        for (int index = 0; index < contestants.length; index++) {
            if (index == 0 || !sameResult(contestants[index], previous)) {
                rank = index + 1;
            }
            System.out.println("rank=" + rank + ", " + contestants[index]);
            previous = contestants[index];
        }
    }

    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C006", "Amy", 92, 310),
            new Contestant("C002", "Ben", 88, 280),
            new Contestant("C009", "Cindy", 92, 295),
            new Contestant("C001", "David", 75, 260),
            new Contestant("C004", "Eva", 88, 240),
            new Contestant("C007", "Frank", 100, 360),
            new Contestant("C003", "Grace", 92, 295),
            new Contestant("C008", "Henry", 100, 330)
        };

        insertionSort(contestants);
        printRanking(contestants);
    }
}
