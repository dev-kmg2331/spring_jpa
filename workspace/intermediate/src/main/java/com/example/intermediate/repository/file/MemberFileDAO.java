package com.example.intermediate.repository.file;

import com.example.intermediate.entity.file.MemberFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberFileDAO {
    @PersistenceContext EntityManager em;

    public MemberFile save(MemberFile memberFile) {
        em.persist(memberFile);
        return memberFile;
    }

    public Optional<MemberFile> findById(Long id) {
        return Optional.ofNullable(em.find(MemberFile.class, id));
    }

    public List<MemberFile> findAll(){
        // 상속관계일 경우 자식 엔티티를 조회하면 자동으로 부모 테이블과 JOIN 된다.
        String query = "select m from MemberFile m";
        return em.createQuery(query, MemberFile.class).getResultList();
    }

    public void delete(MemberFile memberFile) {
        em.remove(memberFile);
    }
}
