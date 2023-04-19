package com.example.advanced.repository.board;

import com.example.advanced.entity.board.Board;
import com.example.advanced.entity.board.Reply;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class BoardDAOTest {

    @Autowired
    private BoardDAO boardDAO;

    @Test
    public void saveTest() {
        // given
        Board board = new Board("테스트제목1", "테스트내용1");

        // when
        Board savedBoard = boardDAO.save(board);

        // then
        assertThat(board).isEqualTo(savedBoard);
    }

    @Test
    public void replyTest() {
        // given
        Board board = new Board("테스트 제목1", "테스트 내용1");

        /**
         * 참조 엔티티 모두 영속 상태로 전환해야 한다.
         * 하지만 Cascade에 CascadeType.PERSIST를 설정하면,
         * 자동으로 참조 엔티티까지 영속 상태로 전환되기 때문에
         * 따로 영속화할 필요가 없어진다.
         * */
        board.addReply(new Reply("테스트 댓글 내용"));

        // when
        /**
         * 엔티티를 영속상태로 변경하고 참조 엔티티까지 영속상태로 변경되었다면,
         * 현재 1차 캐시에는 엔티티 및 참조 엔티티 모두 등록되어 있는 상태이다.
         * */
        boardDAO.save(board);

        // then
        /**
         * 영속 상태인 엔티티를 조회하고,
         * 참조 엔티티까지 조회해도 SELECT 쿼리는 실행되지 않는다.(1차 캐시에 모두 등록되어 있기 때문이다)
         *
         * ※ 영속상태에 등록되어 있는 Id 인지 잘 확인하고 단위 테스트를 진행해야 한다.
         * */
        boardDAO.findById(board.getId()).ifPresent(b -> log.info(b.toString()));

        /**
         * ※ HIBERNATE의 쓰기 지연 저장소 쿼리 실행 순서
         * find, update, insert, remove 순서로 실행된다.
         * find를 하기 전 대상 엔티티 쿼리가 있다면, flush 후 find가 진행된다.
         * */
    }

    @Test
    public void findById() {
        // given
        Board board = new Board("테스트 제목1", "테스트 내용1");
        board.addReply(new Reply("테스트 댓글 내용"));

        // when
        boardDAO.save(board);
        board.addReply(new Reply("테스트 댓글 내용2"));

        // then
        boardDAO.findById(board.getId()).map(Board::toString).ifPresent(log::info);
    }

    @Test
    public void findAllTest(){
        // given
        Board board = new Board("테스트 제목1", "테스트 내용1");
        Reply reply = new Reply("테스트 댓글 내용");
        board.addReply(reply);

        // when
        boardDAO.save(board);
        List<Board> boardList = boardDAO.findAll();

        // then
        assertThat(boardList).contains(board);
        assertThat(boardList.get(0).getReplies()).contains(reply);
    }

    @Test
    public void updateTest(){
        // given
        Board board = new Board("테스트 제목1", "테스트 내용1");
        Reply reply = new Reply("테스트 댓글 내용");
        board.addReply(reply);
        board.addReply(new Reply("테스트 댓글 내용2"));

        // when
        boardDAO.save(board);
        board.getReplies().get(0).setReplContent("수정된 댓글 내용");

        // then
        boardDAO.findById(board.getId()).ifPresent(b -> log.info(b.getReplies().toString()));
    }
}