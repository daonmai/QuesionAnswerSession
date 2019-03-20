package qasession.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="text")
    private String text;
    @Column(name="url")
    private String image_url;
    @Column(name="answeredby")
    @NotEmpty(message="answered_by cannot be empty")
    private String answered_by;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="questionid")
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAnswered_by() {
        return answered_by;
    }

    public void setAnswered_by(String answered_by) {
        this.answered_by = answered_by;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


}
