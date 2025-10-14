package htw.webtech.todo.app;
/**
 * Entity Klasse - Datenobjekt
 * Speichert Titel, Staffel und Episode
 */
public class Progress {
    private String title;
    private String type;   // "Film" oder "Serie"
    private int season;
    private int episode;

    public Progress(String title, String type, int season, int episode) {
        this.title = title;
        this.type = type;
        this.season = season;
        this.episode = episode;
    }

    // Getter
    public String getTitle() { return title; }
    public String getType() { return type; }
    public int getSeason() { return season; }
    public int getEpisode() { return episode; }
}
