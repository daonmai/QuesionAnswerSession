package qasession.repository;

import org.springframework.data.repository.CrudRepository;
import qasession.entity.Answer;

public interface AnswerRepository  extends CrudRepository<Answer, Integer> {
}
