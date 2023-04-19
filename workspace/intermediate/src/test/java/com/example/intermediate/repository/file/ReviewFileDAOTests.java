package com.example.intermediate.repository.file;

import com.example.intermediate.entity.file.ReviewFile;
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
class ReviewFileDAOTests {
    @Autowired
    private ReviewFileDAO reviewFileDAO;

    @Test
    public void saveTest() {
        // given
        ReviewFile reviewFile = new ReviewFile(
                "테스트.jpg", "1234-1234-1234", "/23/04/01"
                , 30000L, 0);

        // when
        ReviewFile savedFile = reviewFileDAO.save(reviewFile);

        // then
        assertThat(savedFile).isEqualTo(reviewFile);
    }

    @Test
    public void findByIdTest() {
        // given
        ReviewFile reviewFile = new ReviewFile(
                "테스트.jpg", "1234-1234-1234", "/23/04/01"
                , 30000L, 0);

        // when
        ReviewFile savedFile = reviewFileDAO.save(reviewFile);

        // then
        assertThat(reviewFileDAO.findById(reviewFile.getId()).isPresent()).isTrue();
    }

    @Test
    public void findAllTest() {
        // given
        ReviewFile reviewFile = new ReviewFile(
                "테스트.jpg", "1234-1234-1234", "/23/04/01"
                , 30000L, 0);

        // when
        ReviewFile savedFile = reviewFileDAO.save(reviewFile);

        // then
        assertThat(reviewFileDAO.findAll()).contains(savedFile);
    }

    @Test
    public void updateTest() {
        // given
        ReviewFile reviewFile = new ReviewFile(
                "테스트.jpg", "1234-1234-1234", "/23/04/01"
                , 30000L, 0);

        // when
        ReviewFile savedFile = reviewFileDAO.save(reviewFile);
        savedFile.setOrgName("테스트2.jpg");

        // then
        assertThat(savedFile.getOrgName()).isEqualTo("테스트2.jpg");
    }

    @Test
    public void deleteTest() {
        // given
        ReviewFile reviewFile = new ReviewFile(
                "테스트.jpg", "1234-1234-1234", "/23/04/01"
                , 30000L, 0);

        // when
        ReviewFile savedFile = reviewFileDAO.save(reviewFile);
        reviewFileDAO.delete(savedFile);

        // then
        assertThat(reviewFileDAO.findById(savedFile.getId()).isEmpty()).isTrue();
    }
}