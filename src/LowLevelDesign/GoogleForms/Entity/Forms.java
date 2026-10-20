package LowLevelDesign.GoogleForms.Entity;

import java.time.LocalDateTime;
import java.util.List;

public class Forms {

    private  String form_Id;
    private String name;
    private User userId;
    private List<Questions> questions;
    private List<RadioButtons> buttons;

    private  LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Forms(String form_Id, String name, User userId, List<Questions> questions, List<RadioButtons> buttons, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.form_Id = form_Id;
        this.name = name;
        this.userId = userId;
        this.questions = questions;
        this.buttons = buttons;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getForm_Id() {
        return form_Id;
    }

    public void setForm_Id(String form_Id) {
        this.form_Id = form_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public List<RadioButtons> getButtons() {
        return buttons;
    }

    public void setButtons(List<RadioButtons> buttons) {
        this.buttons = buttons;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
