package qasession.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
public class QASession  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="qasessionid")
    private int qaSessionId;

    @Column(name="host")
    @NotEmpty(message="hostUser cannot be empty")
    private String hostUser;

    @Column(name="starttime")
    @NotNull(message="startTime cannot be empty")
    @DateTimeFormat
    private ZonedDateTime startTime;

    @Column(name="endtime")
    @DateTimeFormat
    @NotNull(message="startTime cannot be empty")
    private ZonedDateTime  endTime;

    @OneToMany(mappedBy="qaSession")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getHostUser() {
        return hostUser;
    }

    public void setHostUser(String hostUser) {
        this.hostUser = hostUser;
    }

    public QASession(int id) {
        this.qaSessionId = id;
    }

    public QASession() {

    }
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }


    public int getQaSessionId() {
        return qaSessionId;
    }

    public void setQaSessionId(int qaSessionId) {
        this.qaSessionId = qaSessionId;
    }
}
