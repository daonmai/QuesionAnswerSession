package qasession.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qasession.entity.QASession;

@Repository
public interface QASessionRepository extends CrudRepository<QASession, Integer> {

    QASession findByQaSessionId(Integer qaSessionId );



    QASession save(QASession qaSession);



}
