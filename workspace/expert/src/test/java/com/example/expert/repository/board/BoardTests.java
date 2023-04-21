package com.example.expert.repository.board;

import com.example.expert.entity.board.Board;
import com.example.expert.entity.board.Like;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class BoardTests {

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private LikeDAO likeDAO;

    @Test
    public void saveTest() {
        // given
        Board board = new Board();
        Like like = new Like();
        board.setBoardTitle("테스트 제목1");
        board.setBoardContent("테스트 내용1");

        like.setMemberId(30L);
        board.addLike(like);

        // when
        Board savedBoard = boardDAO.save(board);

        // then
//        assertThat(savedBoard).isEqualTo(board);
    }

    @Test
    public void updateLikeTest() {
        // given
        Board board = new Board();
        Like like = new Like();
        board.setBoardTitle("테스트 제목2");
        board.setBoardContent("테스트 내용2");

        like.setMemberId(30L);

        Optional<Board> optionalBoard = boardDAO.findById(1L);

        // when

        // then
    }

    @Test
    public void deleteLikeTest(){
        // given
        Optional<Board> board = boardDAO.findById(1L);
        List<Like> likes = board.map(Board::getLikes).get();

        // when
        likeDAO.delete(likes.get(0));
//        likes.remove(0);

        // then
    }
}