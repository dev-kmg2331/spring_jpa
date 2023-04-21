package com.example.expert.repository.board;

import com.example.expert.entity.board.Like;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LikeDAO {
    @PersistenceContext EntityManager em;

    public Like save(Like like) {
        em.persist(like);
        return like;
    }

    public void delete(Like like) {
        em.remove(like);
    }
}
