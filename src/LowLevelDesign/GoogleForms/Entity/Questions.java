package LowLevelDesign.GoogleForms.Entity;

public class Questions {

    private String questionid;
    private  String questionText;
    private  String questionType;
    private  boolean required;
    private String formId;

    public Questions(String questionid, String questionText, String questionType, boolean required, String formId) {
        this.questionid = questionid;
        this.questionText = questionText;
        this.questionType = questionType;
        this.required = required;
        this.formId = formId;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
