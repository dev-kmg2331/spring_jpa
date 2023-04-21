package com.example.expert.repository.inquire;

import com.example.expert.inquire.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDAO {
    @PersistenceContext
    private EntityManager entityManager;

//    추가
    public Answer save(Answer answer){
        entityManager.persist(answer);
        return answer;
    }

//    조회
    public Optional<Answer> findById(Long id){
        String query = "select a from Answer a";
        return Optional.ofNullable(entityManager.createQuery(query, Answer.class).getSingleResult());
    }

//    전체 조회
    public List<Answer> findAll(){
        String query = "select a from Answer a";
        return entityManager.createQuery(query, Answer.class).getResultList();
    }

//    삭제
    public void delete(Answer answer){
        entityManager.remove(answer);
    }
}
