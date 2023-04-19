package com.example.basic.domain.entity;

import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EntityTest {
    @PersistenceContext
    private EntityManager em;

    // 트랜잭션 영역에서만 DML을 사용할 수 있다.
    // 단위 테스트에서는 자동으로 Rollback 되기 때문에 false로 설정한다.
    @Test
    @Transactional
    @Rollback(false)
    public void saveTest() {
        //given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2331@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        //when
        // Persistence Context에 등록
        em.persist(member);
        // 쓰기 지연 저장소에 있었던 SQL을 DB로 전송, commit() 전에 자동으로 실행
        // 쓰기 지연 저장소가 비워진다.
        // 1차 캐시는 그대로 유지된다.
        em.flush();
        em.clear();

        //then
        assertThat(member.getId()).isEqualTo(em.find(Member.class, member.getId()).getId());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveTest2() {
        // given
        Member memberA = new Member();
        memberA.setMemberName("강민구");
        memberA.setMemberPassword("1234");
        memberA.setMemberEmail("kmg2331@gmail.com");
        memberA.setMemberType(MemberType.MEMBER);
        memberA.setMemberAge(20);

        Member admin = new Member();
        admin.setMemberName("관리자");
        admin.setMemberPassword("1234");
        admin.setMemberEmail("admin1234@gmail.com");
        admin.setMemberType(MemberType.ADMIN);
        admin.setMemberAge(20);

        // when
        em.persist(memberA);
        em.persist(admin);
        em.flush();

        // 1차 캐시 전체 삭제
//        em.clear();

        // 1차 캐시에 조회할 엔티티가 있다면, SQL 조회 없이 1차 캐시에서 가져온다(성능 최적화)
        Member foundMember1 = em.find(Member.class, memberA.getId());

//        em.flush();
//        em.clear();

        Member foundMember2 = em.find(Member.class, memberA.getId());

        // 엔티티 프록시가 this 와 1차 캐시의 엔티티의 내용을 비교하여
        // 변경 감지(더티 체킹) 후 변경사항이 있다면 update 쿼리 실행
        foundMember1.setMemberAge(99);

        foundMember1 = em.find(Member.class, foundMember1.getId());

        // then
        // 1차 캐시에 등록된 엔티가 있다면 동일성이 보장된다.
        assertThat(foundMember1).isEqualTo(foundMember2);
        assertThat(foundMember1.getMemberAge()).isEqualTo(99);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2331@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        em.persist(member);

        // 영속 상태 ; 1차 캐시에 등록된 상태
        // 준영속 상태 : detached instance이며, detached()를 사용하여 1차 캐시로부터 분리된 상태
        // 비영속 상태 : 1차 캐시에 등록되지 않은 상태
        // 영속 상태인 객체일 경우에만 삭제가 가능하다.
//        em.flush();
//        em.clear();

        em.remove(member);

        // then
        assertThat(em.find(Member.class, member.getId())).isNull();
    }
}