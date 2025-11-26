package entity;
// this is a proposed Entity
public class Video {
    private final String url;
    private final String title;
    private final double duration;
    /**
     * Creates a video with the given url, title, and duration.
     * @param url the URL
     * @param title the title
     * @param duration the duration
     */
    public Video(String url, String title, double duration) {
        if ("".equals(url)) {
            throw new IllegalArgumentException("Video url can't be empty.");
        }
        if (duration < 0) {
            throw new IllegalArgumentException("Video duration can't be negative.");
        }
        this.url = url;
        this.title = title;
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }
}
