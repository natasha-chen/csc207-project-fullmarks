package use_case.create_playlist;

public class CreatePlaylistInputData {
    private final String name;

    public CreatePlaylistInputData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}