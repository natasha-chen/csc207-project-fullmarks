package entity;

/**
 * Factory for creating Video objects.
 */
public class VideoFactory {

    public Video create(String url, String title, double duration) {
        return  new Video(url, title, duration);
    }
}
