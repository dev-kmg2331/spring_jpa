package com.example.advanced.repository.user;

import com.example.advanced.entity.user.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
    @PersistenceContext EntityManager em;

    //    추가
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    //    회원 조회
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    //    회원 게시글 조회
    public List<Member> findBoardById(Long id) {
        String query = "select m from Member m inner join fetch m.boards where m.id = :id";
        return em.createQuery(query, Member.class)
                .setParameter("id", id)
                .getResultList();
    }

}
