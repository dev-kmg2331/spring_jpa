package com.example.advanced.repository.member;

import com.example.advanced.entity.member.Address;
import com.example.advanced.entity.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class MemberDAOTest {

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void saveTest() {
        // given
        Address address = new Address("의왕시 경수대로 262", "서해그랑블", "11111");
        Member member = new Member(
                "kmg2331", "1234", "kmg2331@gmail.com", address);

        // when
        Member savedMember = memberDAO.save(member);

        // then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    public void updateTest() {
        // given
        Address address = new Address("의왕시 경수대로 262", "서해그랑블", "11111");
        Member member = new Member(
                "kmg2331", "1234", "kmg2331@gmail.com", address);

        // when
        Member savedMember = memberDAO.save(member);
        savedMember.setMemberId("rkdalsrn2331");

        // then
        assertThat(member.getMemberId()).isEqualTo("rkdalsrn2331");
    }

    @Test
    public void findByIdTest(){
        // given
        Address address = new Address("의왕시 경수대로 262", "서해그랑블", "11111");
        Member member = new Member(
                "kmg2331", "1234", "kmg2331@gmail.com", address);

        // when
        memberDAO.save(member);
        Optional<Member> foundMember = memberDAO.findById(member.getId());

        // then
        assertThat(foundMember.isPresent()).isTrue();
        foundMember.ifPresent(m -> assertThat(m).isEqualTo(member));
    }

    @Test
    public void findAllTest(){
        // given
        Address address = new Address("의왕시 경수대로 262", "서해그랑블", "11111");
        Member member = new Member(
                "kmg2331", "1234", "kmg2331@gmail.com", address);

        // when
        Member savedMember = memberDAO.save(member);
        List<Member> members = memberDAO.findAll();

        // then
        assertThat(members).contains(savedMember);
    }
}