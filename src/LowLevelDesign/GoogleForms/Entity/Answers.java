package LowLevelDesign.GoogleForms.Entity;

public class Answers {

    private String answerId;
    private String questionId;
    private String AnswerText;

    public Answers(String answerId, String questionId, String answerText) {
        this.answerId = answerId;
        this.questionId = questionId;
        AnswerText = answerText;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return AnswerText;
    }

    public void setAnswerText(String answerText) {
        AnswerText = answerText;
    }
}
