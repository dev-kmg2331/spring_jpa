package com.example.intermediate.repository.user;

import com.example.intermediate.entity.file.MemberFile;
import com.example.intermediate.entity.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class MemberDAOTests {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void saveTest() {
        // given
        Member member = new Member("kmg2331", "1234"
                , "강민구", "경기 의왕시", "980130-12341234");

        // when
        Member savedMember = memberDAO.save(member);

        // then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    public void findByIdTest() {
        // given
        Member member = new Member("kmg2331", "1234"
                , "강민구", "경기 의왕시", "980130-12341234");

        // when
        Member savedMember = memberDAO.save(member);
        memberDAO.findById(savedMember.getId()).ifPresent(m -> m.addMemberFiles(
                new MemberFile("테스트.png", "1234-1234-1234", "/23/04/01", 10000L, 0)));

        // then
    }

    @Test
    public void findAllTest() {
        // given
        Member member = new Member("kmg2331", "1234"
                , "강민구", "경기 의왕시", "980130-12341234");

        // when
        Member savedMember = memberDAO.save(member);

        // then
        assertThat(memberDAO.findAll()).contains(savedMember);
    }

    @Test
    public void updateTest() {
        // given
        Member member = new Member("kmg2331", "1234"
                , "강민구", "경기 의왕시", "980130-12341234");
        String changeName = "홍길동";

        // when
        Member savedMember = memberDAO.save(member);
        savedMember.changeProfile(member.getPassword(), changeName, member.getAddress());

        // then
        assertThat(savedMember.getName()).isEqualTo(changeName);
    }

    @Test
    public void deleteTest() {
        // given
        Member member = new Member("kmg2331", "1234"
                , "강민구", "경기 의왕시", "980130-12341234");
        String changeName = "홍길동";

        // when
        Member savedMember = memberDAO.save(member);
        memberDAO.delete(savedMember);

        // then
        assertThat(memberDAO.findById(savedMember.getId()).isEmpty()).isTrue();
    }
}