import java.util.Stack;

public class TextEditorUndoSystem {
    private String text;
    private Stack<String> history;

    public TextEditorUndoSystem() {
        text = "";
        history = new Stack<>();
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 文字編輯撤銷系統 ===");

        System.out.println("\n操作 1：顯示初始內容");
        editor.showText();

        System.out.println("\n操作 2：新增文字 Java");
        editor.addText("Java");

        System.out.println("\n操作 3：新增文字 Programming");
        editor.addText(" Programming");

        System.out.println("\n操作 4：新增文字 Course");
        editor.addText(" Course");

        System.out.println("\n操作 5：刪除最後 7 個字元");
        editor.deleteLastCharacters(7);

        System.out.println("\n操作 6：新增文字 Practice");
        editor.addText("Practice");

        System.out.println("\n操作 7：第一次撤銷");
        editor.undo();

        System.out.println("\n操作 8：第二次撤銷");
        editor.undo();

        System.out.println("\n操作 9：第三次撤銷");
        editor.undo();

        System.out.println("\n操作 10：第四次撤銷");
        editor.undo();

        System.out.println("\n操作 11：沒有紀錄時再次撤銷");
        editor.undo();

        System.out.println("\n=== 最後內容 ===");
        editor.showText();
    }

    public void addText(String newText) {
        if (newText == null || newText.isEmpty()) {
            System.out.println("新增失敗，文字不能是空白");
            return;
        }

        saveCurrentState();
        text += newText;

        System.out.println("新增成功");
        showText();
    }

    public void deleteLastCharacters(int count) {
        if (count <= 0) {
            System.out.println("刪除失敗，刪除數量必須大於 0");
            return;
        }

        if (text.isEmpty()) {
            System.out.println("刪除失敗，目前沒有文字");
            return;
        }

        if (count > text.length()) {
            System.out.println(
                    "刪除失敗，刪除數量超過目前文字長度"
            );
            return;
        }

        saveCurrentState();

        text = text.substring(0, text.length() - count);

        System.out.println("已刪除最後 " + count + " 個字元");
        showText();
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("撤銷失敗，目前沒有歷史紀錄");
            return;
        }

        text = history.pop();

        System.out.println("撤銷成功");
        showText();
    }

    private void saveCurrentState() {
        history.push(text);
    }

    public void showText() {
        if (text.isEmpty()) {
            System.out.println("目前內容：空白");
        } else {
            System.out.println("目前內容：" + text);
        }
    }
}