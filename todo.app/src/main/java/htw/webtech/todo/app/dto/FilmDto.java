package htw.webtech.todo.app.dto;

public class FilmDto {
    private Long id;
    private String title;
    private Integer minutes; // plural in der API-Antwort
    private String notes;

    public FilmDto() { }

    public FilmDto(Long id, String title, Integer minutes, String notes) {
        this.id = id;
        this.title = title;
        this.minutes = minutes;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getMinutes() { return minutes; }
    public void setMinutes(Integer minutes) { this.minutes = minutes; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
