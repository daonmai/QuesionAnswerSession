package qasession.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="questionid")
    private int questionId;

    @NotEmpty(message="Question text cannot be empty")
    @Column(name="text")
    private String text;

    @NotEmpty(message="asked_by cannot be empty")
    @Column(name="askedby")
    private String asked_by;

    @OneToOne(mappedBy = "question")
    private Answer answer;


    @ManyToOne
    @JoinColumn(name="qaSessionId")
    @JsonIgnore
    private QASession qaSession;


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int id) {
        this.questionId = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getAsked_by() {
        return asked_by;
    }

    public void setAsked_by(String asked_by) {
        this.asked_by = asked_by;
    }

    public QASession getQaSession() {
        return qaSession;
    }

    public void setQaSession(QASession qaSession) {
        this.qaSession = qaSession;
    }
}
