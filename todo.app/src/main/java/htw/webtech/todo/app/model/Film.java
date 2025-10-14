package htw.webtech.todo.app.model;

public class Film {
    private Long id;
    private String title;
    private Integer minute;     // Zwischenstand
    private String notes;       // Eigene Notizen (Bearbeiter)

    public Film() {}
    public Film(Long id, String title, Integer minute, String notes) {
        this.id = id; this.title = title; this.minute = minute; this.notes = notes;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getMinute() { return minute; }
    public String getNotes() { return notes; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setMinute(Integer minute) { this.minute = minute; }
    public void setNotes(String notes) { this.notes = notes; }
}
