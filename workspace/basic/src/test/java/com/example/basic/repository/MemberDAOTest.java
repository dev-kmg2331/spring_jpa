package com.example.basic.repository;

import com.example.basic.domain.entity.Member;
import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
class MemberDAOTest {

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void saveTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2331@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        Member savedMember = memberDAO.save(member);
        savedMember.setMemberAge(99);

        // then
        assertThat(member.getMemberAge()).isEqualTo(memberDAO.findById(member.getId()).get().getMemberAge());
    }

    @Test
    public void findAllTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2332@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        memberDAO.save(member);

        List<Member> memberList = memberDAO.findAll();

        // then
        assertThat(memberList).contains(member);
    }

    @Test
    public void findByIdTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2333@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        memberDAO.save(member);
        Optional<Member> optionalMember = memberDAO.findById(member.getId());

        // then
    }

    @Test
    public void deleteTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2333@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        memberDAO.save(member);

        memberDAO.delete(member);

        // then
        assertThat(memberDAO.findById(member.getId())).isEqualTo(Optional.empty());
    }

    @Test
    public void findAllWithPagingTest() {
        // given

        // when
        for (int i = 0; i < 50; i++) {
            Member member = new Member();
            member.setMemberName("강민구" + i);
            member.setMemberPassword("1234");
            member.setMemberEmail("kmg2333@gmail.com" + i);
            member.setMemberType(MemberType.MEMBER);
            member.setMemberAge(20 + i);
            memberDAO.save(member);
        }

        // then
        memberDAO.findAllWithPaging(1, 10, "memberAge").stream().map(Member::getMemberName).forEach(log::info);
    }

    @Test
    public void findByMemberNameTest() {
        // given
        Member member = new Member();
        member.setMemberName("강민구");
        member.setMemberPassword("1234");
        member.setMemberEmail("kmg2333@gmail.com");
        member.setMemberType(MemberType.MEMBER);
        member.setMemberAge(20);

        // when
        memberDAO.save(member);
        List<Member> foundMembers = memberDAO.findByMemberName("강민구");

        // then
        assertThat(foundMembers).contains(member);
    }

    @Test
    public void deleteByAgeGreaterThanEqualTest() {
        // given
        int age = 40;
        for (int i = 0; i < 50; i++) {
            Member member = new Member();
            member.setMemberName("강민구" + i);
            member.setMemberPassword("1234");
            member.setMemberEmail("kmg2333@gmail.com" + i);
            member.setMemberType(MemberType.MEMBER);
            member.setMemberAge(20 + i);
            memberDAO.save(member);
        }

        // when
        memberDAO.deleteByAgeGreaterThanEqual(age);

        // then
        memberDAO.findAll().forEach(member -> {
            log.info("회원 " + member.getId() + " 통과");
            assertThat(member.getMemberAge()).isLessThan(age);
        });
    }

    @Test
    public void deleteByAgeGreaterThanEqual() {
        // given

        // when

        // then
    }

}