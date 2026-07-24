public class PlaylistNode {
    private String code;
    private String songName;
    PlaylistNode next;

    public PlaylistNode(String code, String songName) {
        this.code = code.trim();
        this.songName = songName.trim();
        this.next = null;
    }

    public String getCode() {
        return code;
    }

    public String getSongName() {
        return songName;
    }

    @Override
    public String toString() {
        return code + " | " + songName;
    }
}