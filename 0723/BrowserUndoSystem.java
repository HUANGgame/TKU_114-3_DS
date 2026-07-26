import java.util.Stack;

public class BrowserUndoSystem {
    private Stack<String> pageHistory;

    public BrowserUndoSystem() {
        pageHistory = new Stack<>();
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 瀏覽器返回系統測試 ===");

        System.out.println("\n操作 1：查看空白瀏覽器");
        browser.showCurrentPage();

        System.out.println("\n操作 2：空紀錄時返回上一頁");
        browser.goBack();

        System.out.println("\n操作 3：開啟 Google");
        browser.openPage("Google");

        System.out.println("\n操作 4：開啟 YouTube");
        browser.openPage("YouTube");

        System.out.println("\n操作 5：開啟 GitHub");
        browser.openPage("GitHub");

        System.out.println("\n操作 6：查看目前頁面");
        browser.showCurrentPage();

        System.out.println("\n操作 7：返回上一頁");
        browser.goBack();

        System.out.println("\n操作 8：再次返回上一頁");
        browser.goBack();

        System.out.println("\n操作 9：已在第一頁時返回");
        browser.goBack();

        System.out.println("\n操作 10：開啟淡江大學");
        browser.openPage("淡江大學");

        System.out.println("\n=== 最後瀏覽紀錄 ===");
        browser.printHistory();
    }

    public void openPage(String pageName) {
        if (pageName == null || pageName.trim().isEmpty()) {
            System.out.println("開啟失敗，頁面名稱不能是空白");
            return;
        }

        pageHistory.push(pageName.trim());

        System.out.println("已開啟頁面：" + pageHistory.peek());
    }

    public void goBack() {
        if (pageHistory.isEmpty()) {
            System.out.println("返回失敗，目前沒有開啟任何頁面");
            return;
        }

        if (pageHistory.size() == 1) {
            System.out.println("無法返回，已經是第一個頁面");
            System.out.println("目前頁面：" + pageHistory.peek());
            return;
        }

        String closedPage = pageHistory.pop();

        System.out.println("已離開頁面：" + closedPage);
        System.out.println("返回頁面：" + pageHistory.peek());
    }

    public void showCurrentPage() {
        if (pageHistory.isEmpty()) {
            System.out.println("目前沒有開啟任何頁面");
            return;
        }

        System.out.println("目前頁面：" + pageHistory.peek());
    }

    public void printHistory() {
        if (pageHistory.isEmpty()) {
            System.out.println("目前沒有瀏覽紀錄");
            return;
        }

        System.out.println("由最早到目前頁面：");

        for (int i = 0; i < pageHistory.size(); i++) {
            System.out.println((i + 1) + ". " + pageHistory.get(i));
        }

        System.out.println("目前頁面：" + pageHistory.peek());
    }
}