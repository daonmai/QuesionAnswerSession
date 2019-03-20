package qasession.repository;

import org.springframework.data.repository.CrudRepository;

import qasession.entity.QASession;
import qasession.entity.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {


    List<Question> findByQaSessionAndAnswerIsNull(QASession qaSession);
    List<Question> findByQaSessionAndAnswerIsNotNull(QASession qaSession);
    Question findQuestionByQuestionId(int questionId);
   // Question findByQuestionId(int questionId);

}
