package qasession.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import qasession.entity.Answer;
import qasession.entity.QASession;
import qasession.entity.Question;
import qasession.exception.InvalidRequestException;
import qasession.repository.AnswerRepository;
import qasession.repository.QASessionRepository;
import qasession.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QASessionService {

    @Autowired
    QASessionRepository qaSessionRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;


    public QASession createQASssion(  QASession qaSession) {


            return qaSessionRepository.save(qaSession);

    }

    public QASession getQASession(int qaSessionId) {

        return qaSessionRepository.findByQaSessionId(qaSessionId);
    }

    public Question postQuestion(int qaSessionId, Question question) throws InvalidRequestException{

        QASession session = qaSessionRepository.findByQaSessionId(qaSessionId);
        if ( session == null)
            throw new InvalidRequestException("Invalid QA Session");

        question.setQaSession(session);
        return questionRepository.save(question);

    }

    public Answer answerQuestion(Answer answer, int questionId) throws InvalidRequestException {

        if ( StringUtils.isEmpty(answer.getText()) || StringUtils.isEmpty(answer.getImage_url()))
            throw new InvalidRequestException("Either text or image url has to be provided.");
        Question question = questionRepository.findQuestionByQuestionId(questionId);

        if ( question == null)
            throw new InvalidRequestException("Cannot find question to answer");

        answer.setQuestion(question);
        return answerRepository.save(answer);

    }

    public List<Question> listQuestions(int qaSessionId, @Nullable Boolean isAnswered) throws InvalidRequestException{

        QASession qaSession = qaSessionRepository.findByQaSessionId(qaSessionId);
        List<Question> questions = new ArrayList();
        if (qaSession == null)
            throw new InvalidRequestException("Invalid QA Session");
        if (isAnswered != null) {

            if (qaSession.getQuestions() != null) {
                if (isAnswered == true) {

                    return qaSession.getQuestions().stream().filter(question -> question.getAnswer() != null)
                            .collect(Collectors.toList());
                }
                else
                    return qaSession.getQuestions().stream().filter(question -> question.getAnswer() == null)
                            .collect(Collectors.toList());
            }
            else {
                return questions;

            }
        }
        else {
            return qaSession.getQuestions();
        }
    }
}

