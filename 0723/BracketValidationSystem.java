import java.util.Stack;

public class BracketValidationSystem {

    public static void main(String[] args) {
        System.out.println("=== 括號驗證系統 ===");

        testExpression("()");
        testExpression("([])");
        testExpression("{[()]}");
        testExpression("a + (b * [c - d])");
        testExpression("([)]");
        testExpression("abc]");
        testExpression("{abc");
        testExpression("沒有任何括號");
        testExpression("");
    }

    public static void testExpression(String expression) {
        System.out.println("測試內容：" + expression);

        if (isValid(expression)) {
            System.out.println("驗證結果：括號正確");
        } else {
            System.out.println("驗證結果：括號錯誤");
        }

        System.out.println();
    }

    public static boolean isValid(String expression) {
        if (expression == null) {
            System.out.println("輸入內容不能是 null");
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (isLeftBracket(current)) {
                stack.push(current);
            } else if (isRightBracket(current)) {
                if (stack.isEmpty()) {
                    System.out.println(
                            "錯誤位置：" + i
                                    + "，缺少對應的左括號"
                    );
                    return false;
                }

                char leftBracket = stack.pop();

                if (!isMatchingPair(leftBracket, current)) {
                    System.out.println(
                            "錯誤位置：" + i
                                    + "，括號順序不正確"
                    );
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("驗證失敗，缺少右括號");
            return false;
        }

        return true;
    }

    public static boolean isLeftBracket(char value) {
        return value == '('
                || value == '['
                || value == '{';
    }

    public static boolean isRightBracket(char value) {
        return value == ')'
                || value == ']'
                || value == '}';
    }

    public static boolean isMatchingPair(
            char leftBracket,
            char rightBracket) {

        return (leftBracket == '(' && rightBracket == ')')
                || (leftBracket == '[' && rightBracket == ']')
                || (leftBracket == '{' && rightBracket == '}');
    }
}