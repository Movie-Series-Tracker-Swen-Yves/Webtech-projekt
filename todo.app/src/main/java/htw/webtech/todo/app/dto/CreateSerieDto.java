package htw.webtech.todo.app.dto;

public class CreateSerieDto {
    private String title;
    private Integer season;
    private Integer episode;
    private Integer minutes;
    private String notes;

    public CreateSerieDto() {}

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
