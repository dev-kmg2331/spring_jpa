package com.example.basic.repository;

import com.example.basic.domain.entity.Member;
import com.example.basic.type.MemberType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
//  1. application.yml 파일에 작성된 Connection 정보를 통해 EntityManagerFactory가 생성된다.
//  2. EntityManagerFactory를 통해 EntityManager객체가 생성된다.

    @PersistenceContext // EntityManager 를 통해서 생성된 Entity 객체를 등록하는 영역
            EntityManager em; // EntityManagerFactory를 통해 생성되며, Connection 객체를 통해 SQL문을 제작해준다.

    // 등록하자마자 바로 사용 -> SpringDataJPA 의 내부 메소드 구조
    public Member save(Member member) {
        em.persist(member);
        em.flush();
        return member;
    }

    public Optional<Member> findById(Long id) {
//        Optional.ofNullable(em.find(Member.class, id)).ifPresentOrElse(member -> {}, () -> {});

        // Optional 은 NULL 이 아니기 때문에 NPE를 방지할 수 있으며,
        // 필드로 들어간 엔티티의 NULL 검사를 편하게 할 수 있는 API 이다.
        // 단, Optional 사용은 리턴 타입에서만 사용하는 것을 권장한다.
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public void delete(Member member) {
        em.remove(member);
    }

    // JPQL - 객체 지향 쿼리 언어
    // 엔티티 객체를 대상으로 쿼리를 작성해야 한다.
    // JPQL은 SQL로 변환된다.
    // 키워드는 대소문자 구분이 없다.
    // 별칭(as) 필수
    // TypedQuery : 리턴 타입을 정확히 알 때, 전달한 타입으로 리턴된다.
    // Query : 리턴 타입이 정확하지 않을 때, Object로 리턴된다.

    // 전체 조회
    public List<Member> findAll() {
        String query = "select m from Member m"; // Inject Language or reference -> JPA QL
        return em.createQuery(query, Member.class).getResultList(); // TypedQuery
//        return em.createQuery(query).getResultList(); // Query
    }

    // 전체 조회 페이징 처리
    public List<Member> findAllWithPaging(int startRow, int rowCount) {
        String query = "select m from Member m";
        return em.createQuery(query, Member.class)
                .setFirstResult(startRow)
                .setMaxResults(rowCount)
                .getResultList();
    }

    // 전체 조회 페이징 처리
    public List<Member> findAllWithPaging(int startRow, int rowCount, String orderBy) {
        String query = "select m from Member m";

        if (orderBy != null) {
            if (orderBy.equals("memberAge")) query += " order by m." + "memberAge" + " desc";
        }

        return em.createQuery(query, Member.class)
                .setFirstResult(startRow - 1)
                .setMaxResults(rowCount)
                .getResultList();
    }

    // 특정 회원 조회
    public List<Member> findByMemberName(String memberName) {
        String query = "select m from Member m where m.memberName = :memberName";
        return em.createQuery(query, Member.class)
                .setParameter("memberName", memberName)
                .getResultList();
    }

    // 특정 회원 삭제
    public void deleteByAgeGreaterThanEqual(int age) {
        String query = "delete from Member m where m.memberAge >= :age";
        em.createQuery(query).setParameter("age", age).executeUpdate();
    }

    public void updateByMemberAgeLessThanEqual(int age) {
        String query = "update Member m set m.memberType = :memberType " +
                "where m.memberAge < :age";
        em.createQuery(query)
                .setParameter("memberType", MemberType.ADMIN)
                .setParameter("age", age)
                .executeUpdate();
    }
}
