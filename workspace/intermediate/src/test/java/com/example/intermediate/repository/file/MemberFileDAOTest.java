package com.example.intermediate.repository.file;

import com.example.intermediate.entity.file.MemberFile;
import com.example.intermediate.entity.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class MemberFileDAOTest {
    @Autowired
    private MemberFileDAO memberFileDAO;

    @Test
    public void saveTest(){
        // given

        // when

        // then
    }
}