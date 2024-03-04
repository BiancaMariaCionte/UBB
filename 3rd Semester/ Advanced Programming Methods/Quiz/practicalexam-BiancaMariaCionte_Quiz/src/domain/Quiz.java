package domain;

public class Quiz {
    private Integer id;
    private String text;
    private String correctAnswer;
    private Integer score;
    private String userAnswer;

    public Quiz(Integer id, String text, String correctAnswer, Integer score, String userAnswer) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", score=" + score +
                '}';
    }
}
