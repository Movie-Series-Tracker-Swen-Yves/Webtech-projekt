package htw.webtech.todo.app.model;

public class Serie {
    private Long id;
    private String title;
    private Integer season;
    private Integer episode;
    private Integer minute;     // optional
    private String notes;

    public Serie() {}
    public Serie(Long id, String title, Integer season, Integer episode, Integer minute, String notes) {
        this.id = id; this.title = title; this.season = season; this.episode = episode; this.minute = minute; this.notes = notes;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getSeason() { return season; }
    public Integer getEpisode() { return episode; }
    public Integer getMinute() { return minute; }
    public String getNotes() { return notes; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setSeason(Integer season) { this.season = season; }
    public void setEpisode(Integer episode) { this.episode = episode; }
    public void setMinute(Integer minute) { this.minute = minute; }
    public void setNotes(String notes) { this.notes = notes; }
}
