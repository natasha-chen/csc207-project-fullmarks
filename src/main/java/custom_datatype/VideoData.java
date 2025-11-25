package custom_datatype;

/**
 * Not to be confused with the Entity Video.
 */
public class VideoData {
    private final Integer id;
    private final String url;
    private final String title;
    private boolean mp3Bool;

    public VideoData(Integer id, String url, String title) {
        /*This will tell the Interactor which url to download in what format.*/
        /*Title is the least useful in this Use Case, but may be relayed to Progress Use Case*/
        this.id = id;
        this.url = url;
        this.title = title;
        this.mp3Bool = false;
    }

    public int getId() {return id;}

    public String getUrl() {return url;}

    public String getTitle() {return title;}

    public boolean getMp3Bool() {return mp3Bool;}

    public void setMp3Bool(boolean mp3Bool) {this.mp3Bool = mp3Bool;}


    @Override
    public String toString() {
        return "VideoData{"
                + "id=" + id
                + ", url='" + url + '\n'
                + "title='" + title + '\n'
                + "mp3bool=" + mp3Bool
                + '}';
    }
}
