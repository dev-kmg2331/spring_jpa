package com.example.expert.repository.Inquire;

import com.example.expert.entity.Inquire.Answer;
import com.example.expert.entity.Inquire.Question;
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
class InquireTests {

    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private AnswerDAO answerDAO;

    @Test
    public void saveTest() {
//        INSERT 실행 시, @JoinColumn으로 설정된 객체로만 FK를 추가할 수 있다.
//        @JoinColumn이 설정되지 않은 연관객체로는 FK를 추가할 수 없다.
//        하지만, 양방향 관계에서는
//        @JoinColumn을 사용하지 않아도 mappedBy로 FK를 설정한다.
//        mappedBy를 생략하면 모든 테이블에 FK가 생긴다.

//        RDB에서 설계할 때 N 쪽에 FK를 두기 때문에
//        FK를 필드로 가지고 있는 엔티티가 연관관계의 주인이 되어야 한다.

//        Answer answer = new Answer();
//        Question question = new Question();
//
//        answer.setAnswerContents("답변 내용1");
//        questionDAO.save(answer);
//
//        question.setQuestionTitle("문의 제목1");
//        question.setQuestionContents("문의 내용1");
//        question.setAnswer(answer);
//
//        questionDAO.save(question);
//        Answer answer = new Answer();
//        Question question = new Question();
//
//        question.setQuestionTitle("문의 제목1");
//        question.setQuestionContents("문의 내용1");
//        questionDAO.save(question);
//
//        answer.setAnswerContents("답변 내용1");
//        answer.setQuestion(question);
//
//        questionDAO.save(answer);
    }

    @Test
    public void updateTest() {
        // given


        // when

        // then
    }
}