package com.example.advanced.repository.member;

import com.example.advanced.entity.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
    @PersistenceContext EntityManager em;

    // 추가
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // 조회
    public Optional<Member> findById(Long id) {
//        String query = "select m from Member m where m.id = :id";
//
//        return em.createQuery(query, Member.class).setParameter("id", id).getSingleResult();

        return Optional.ofNullable(em.find(Member.class, id));
    }

    // 전체조회
    public List<Member> findAll() {
        String query = "select m from Member m ";

        return em.createQuery(query, Member.class).getResultList();
    }
}
