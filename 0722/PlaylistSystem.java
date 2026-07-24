import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        int option = -1;

        while (option != 0) {
            printMenu();

            try {
                System.out.print("請輸入選項：");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        addSong(sc, playlist);
                        break;

                    case 2:
                        searchSong(sc, playlist);
                        break;

                    case 3:
                        deleteSong(sc, playlist);
                        break;

                    case 4:
                        playlist.printPlaylist();
                        break;

                    case 0:
                        System.out.println("程式結束");
                        break;

                    default:
                        System.out.println("選項錯誤，請重新輸入");
                }
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤，選項必須是整數");
                sc.nextLine();
                option = -1;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 播放清單系統 ===");
        System.out.println("1. 尾端新增歌曲");
        System.out.println("2. 依代碼搜尋");
        System.out.println("3. 刪除歌曲");
        System.out.println("4. 顯示完整播放順序");
        System.out.println("0. 結束");
    }

    public static void addSong(
            Scanner sc,
            PlaylistLinkedList playlist) {

        System.out.print("請輸入歌曲代碼：");
        String code = sc.nextLine();

        System.out.print("請輸入歌曲名稱：");
        String songName = sc.nextLine();

        playlist.addLast(code, songName);
    }

    public static void searchSong(
            Scanner sc,
            PlaylistLinkedList playlist) {

        if (playlist.isEmpty()) {
            System.out.println("搜尋失敗，播放清單目前是空的");
            return;
        }

        System.out.print("請輸入歌曲代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("搜尋失敗，歌曲代碼不能是空白");
            return;
        }

        PlaylistNode result = playlist.findByCode(code);

        if (result == null) {
            System.out.println("找不到歌曲代碼：" + code);
        } else {
            System.out.println("搜尋結果：" + result);
        }
    }

    public static void deleteSong(
            Scanner sc,
            PlaylistLinkedList playlist) {

        System.out.print("請輸入要刪除的歌曲代碼：");
        String code = sc.nextLine();

        playlist.removeByCode(code);

        System.out.println("刪除後播放順序：");
        playlist.printPlaylist();
    }
}