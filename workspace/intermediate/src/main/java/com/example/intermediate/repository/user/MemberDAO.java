package com.example.intermediate.repository.user;

import com.example.intermediate.entity.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
    @PersistenceContext EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        String query = "select m from Member m";
        return em.createQuery(query, Member.class).getResultList();
    }

    public void delete(Member member) {
        em.remove(member);
        em.clear();
    }
}
