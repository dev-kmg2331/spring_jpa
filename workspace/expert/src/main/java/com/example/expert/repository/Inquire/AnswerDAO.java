package com.example.expert.repository.Inquire;

import com.example.expert.entity.Inquire.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDAO {
    @PersistenceContext EntityManager em;

    // 저장
    public Answer save(Answer answer) {
        em.persist(answer);
        return answer;
    }

    // 조회
    public Optional<Answer> findById(Long id) {
        return Optional.of(em.find(Answer.class, id));
    }

    // 전체조회
    public List<Answer> findAll() {
        String query = "select a from Answer a";

        return em.createQuery(query, Answer.class).getResultList();
    }

    // 삭제
    public void delete(Answer answer) {
        em.remove(answer);
    }
}
