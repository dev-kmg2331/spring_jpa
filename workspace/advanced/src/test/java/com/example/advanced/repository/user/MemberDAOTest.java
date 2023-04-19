package com.example.advanced.repository.user;

import com.example.advanced.entity.board.Board;
import com.example.advanced.entity.user.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class MemberDAOTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void boardWriteTest() {
        // given
        Member member = new Member();
        member.addBoards(
                new Board("으앙", "으앙"),
                new Board("으앙2", "으앙2")
        );

        // when
        memberDAO.save(member);

        // then
        memberDAO.findById(member.getId()).map(Member::toString).ifPresent(log::info);
    }
}