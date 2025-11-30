package entity;
 // note: need to ensure every file has unique fileName/ID
public class MediaFile {
    private String title;     // human title that can be edited
    private final String fileID;  // UUID, = actual file name under media/, is a unique key

    public MediaFile(String title, String fileID, String format) {
        this.title = title;
        this.fileID = fileID;
    }

    public String getTitle() { return title; }
    public String getFileID() { return fileID; }
     public void setTitle(String title) { this.title = title; }

}