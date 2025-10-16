package htw.webtech.todo.app.dto;

public class CreateFilmDto {
    private String title;
    private Integer minutes;
    private String notes;

    public CreateFilmDto() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getMinutes() { return minutes; }
    public void setMinutes(Integer minutes) { this.minutes = minutes; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}