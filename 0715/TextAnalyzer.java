import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = readNonBlankText(sc, "請輸入一行文字：");
        String trimmedText = text.trim();
        String[] words = splitWords(trimmedText);

        System.out.println();
        System.out.println("=== Text Analysis ===");
        System.out.println("Original length: " + text.length());
        System.out.println("Valid length: " + trimmedText.length());
        System.out.println("Word count: " + words.length);
        System.out.println("Vowel count: " + countVowels(trimmedText));
        System.out.println("Longest word: " + findLongestWord(words));

        String keyword = readNonBlankText(sc, "請輸入關鍵字：");
        int keywordCount = countKeyword(words, keyword);

        System.out.println("Keyword count: " + keywordCount);

        sc.close();
    }

    public static String readNonBlankText(Scanner sc, String message) {
        System.out.print(message);
        String text = sc.nextLine();

        while (text.trim().isEmpty()) {
            System.out.print("輸入不能是空白，請重新輸入：");
            text = sc.nextLine();
        }

        return text;
    }

    public static String[] splitWords(String text) {
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);

            if (ch == 'a'
                    || ch == 'e'
                    || ch == 'i'
                    || ch == 'o'
                    || ch == 'u') {
                count++;
            }
        }

        return count;
    }

    public static String findLongestWord(String[] words) {
        String longestWord = words[0];

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }

        return longestWord;
    }

    public static int countKeyword(String[] words, String keyword) {
        int count = 0;

        for (String word : words) {
            if (word.equalsIgnoreCase(keyword.trim())) {
                count++;
            }
        }

        return count;
    }
}