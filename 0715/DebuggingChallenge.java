/*
修正紀錄：

1. println 那行少了分號，所以程式不能編譯。
   在最後補上 ;。

2. for 迴圈寫成 i <= scores.length，
   最後會跑到不存在的索引。
   改成 i < scores.length。

3. 字串不能用 == 比內容，
   改成 equalsIgnoreCase()。

4. total 和 scores.length 都是 int，
   直接相除會少掉小數。
   把 total 轉成 double 再算。

5. nextInt() 後面還留著換行，
   所以 nextLine() 會直接讀到空字串。
   中間多加一次 sc.nextLine()。

Breakpoint 紀錄：
i = 0 時，total = 0，scores[i] = 80
i = 1 時，total = 80，scores[i] = 75
i = 2 時，total = 155，scores[i] = 92
迴圈跑完後 total = 247
*/

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equalsIgnoreCase("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}