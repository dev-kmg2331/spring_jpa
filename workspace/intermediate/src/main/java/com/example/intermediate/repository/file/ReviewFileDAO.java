package com.example.intermediate.repository.file;

import com.example.intermediate.entity.file.File;
import com.example.intermediate.entity.file.ReviewFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewFileDAO {
    @PersistenceContext EntityManager em;

    public ReviewFile save(ReviewFile reviewFile) {
        em.persist(reviewFile);
        return reviewFile;
    }

    public Optional<ReviewFile> findById(Long id) {
        return Optional.ofNullable(em.find(ReviewFile.class, id));
    }

    public List<File> findAll() {
        // 상속관계일 경우 부모 엔티티를 조회하면 자동으로 모든 자식 테이블과 JOIN된다.
        String query = "select f from File f";
        return em.createQuery(query, File.class).getResultList();
    }

    public void delete(ReviewFile reviewFile) {
        em.remove(reviewFile);
    }
}
