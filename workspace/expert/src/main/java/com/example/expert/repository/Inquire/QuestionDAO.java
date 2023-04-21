package com.example.expert.repository.Inquire;

import com.example.expert.entity.Inquire.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDAO {
    @PersistenceContext EntityManager em;

    // 저장
    public Question save(Question question) {
        em.persist(question);
        return question;
    }

    // 조회
    public Optional<Question> findById(Long id) {
        String query = "select q from Question q" +
                " inner join fetch q.answer" +
                " where q.id = :id";

        return Optional.ofNullable(em.createQuery(query, Question.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    // 전체조회
    public List<Question> findAll() {
        String query = "select q from Question q" +
                " inner join fetch q.answer";

        return em.createQuery(query, Question.class).getResultList();
    }

    // 삭제
    public void delete(Question question) {
        em.remove(question);
    }
}
