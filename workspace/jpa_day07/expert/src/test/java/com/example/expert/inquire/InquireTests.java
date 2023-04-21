package com.example.expert.inquire;

import com.example.expert.repository.inquire.AnswerDAO;
import com.example.expert.repository.inquire.QuestionDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class InquireTests {

    @Autowired private QuestionDAO questionDAO;
    @Autowired private AnswerDAO answerDAO;

    @Test
    public void saveTest(){
//        INSERT 실행 시, @JoinColumn으로 설정된 객체로만 FK를 추가할 수 있다.
//        @JoinColumn이 설정되지 않은 연관객체로는 FK를 추가할 수 없다.

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
}



















