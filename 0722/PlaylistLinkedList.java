public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public boolean addLast(String code, String songName) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("新增失敗，歌曲代碼不能是空白");
            return false;
        }

        if (songName == null || songName.trim().isEmpty()) {
            System.out.println("新增失敗，歌曲名稱不能是空白");
            return false;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，歌曲代碼已存在");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(code, songName);

        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
        System.out.println("新增成功：" + newNode);
        return true;
    }

    public PlaylistNode findByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        PlaylistNode current = head;

        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code.trim())) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public boolean removeByCode(String code) {
        if (head == null) {
            System.out.println("刪除失敗，播放清單目前是空的");
            return false;
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("刪除失敗，歌曲代碼不能是空白");
            return false;
        }

        String targetCode = code.trim();

        if (head.getCode().equalsIgnoreCase(targetCode)) {
            String deletedSong = head.getSongName();
            head = head.next;
            size--;

            System.out.println("刪除成功：" + deletedSong);
            return true;
        }

        PlaylistNode current = head;

        while (current.next != null) {
            if (current.next.getCode().equalsIgnoreCase(targetCode)) {
                String deletedSong = current.next.getSongName();
                current.next = current.next.next;
                size--;

                System.out.println("刪除成功：" + deletedSong);
                return true;
            }

            current = current.next;
        }

        System.out.println("刪除失敗，找不到歌曲代碼：" + targetCode);
        return false;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單目前是空的");
            return;
        }

        System.out.println("=== 完整播放順序 ===");

        PlaylistNode current = head;
        int order = 1;

        while (current != null) {
            System.out.println(order + ". " + current);
            current = current.next;
            order++;
        }

        System.out.println("歌曲總數：" + size);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}