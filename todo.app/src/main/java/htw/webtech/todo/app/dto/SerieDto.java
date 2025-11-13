package htw.webtech.todo.app.dto;

public class SerieDto {
    private Long id;
    private String title;
    private Integer season;
    private Integer episode;
    private Integer minutes; // plural
    private String notes;

    public SerieDto() { }

    public SerieDto(Long id, String title, Integer season, Integer episode, Integer minutes, String notes) {
        this.id = id;
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.minutes = minutes;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getSeason() { return season; }
    public void setSeason(Integer season) { this.season = season; }

    public Integer getEpisode() { return episode; }
    public void setEpisode(Integer episode) { this.episode = episode; }

    public Integer getMinutes() { return minutes; }
    public void setMinutes(Integer minutes) { this.minutes = minutes; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
