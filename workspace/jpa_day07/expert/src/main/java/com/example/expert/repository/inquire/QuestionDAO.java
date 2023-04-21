package com.example.expert.repository.inquire;

import com.example.expert.inquire.Answer;
import com.example.expert.inquire.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDAO {
    @PersistenceContext
    private EntityManager entityManager;

//    엔티티 추가
    public Question save(Question question){
        entityManager.persist(question);
        return question;
    }

//    참조 엔티티 추가
    public Answer save(Answer answer){
        entityManager.persist(answer);
        return answer;
    }

//    조회
    public Optional<Question> findById(Long id){
        String query = "select q from Question q join fetch q.answer where q.id = :id";
        return Optional.ofNullable(
            entityManager
                    .createQuery(query, Question.class)
                    .setParameter("id", id)
                    .getSingleResult());
    }

//    전체 조회
    public List<Question> findAll(Long id){
        String query = "select q from Question q join fetch q.answer";
        return entityManager.createQuery(query, Question.class).getResultList();
    }

//    삭제
    public void delete(Question question){
        entityManager.remove(question);
    }
}















