package htw.webtech.todo.app.model;

import jakarta.persistence.*;

@Entity
public class Serie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer season;
    private Integer episode;

    // Entity: singular
    private Integer minute;

    @Column(length = 2000)
    private String notes;

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getSeason() { return season; }
    public void setSeason(Integer season) { this.season = season; }

    public Integer getEpisode() { return episode; }
    public void setEpisode(Integer episode) { this.episode = episode; }

    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
