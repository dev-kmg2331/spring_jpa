package com.example.expert.entity.board;

import com.example.expert.audit.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_BOARD")
@Getter @Setter
public class Board extends Period {
    // 게시글 번호
    @Id @GeneratedValue
    private Long id;
    // 게시글 제목
    private String boardTitle;
    // 게시글 내용
    private String boardContent;

    @OneToMany(
            mappedBy = "board",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST}/*, orphanRemoval = true*/)
    private List<Like> likes = new ArrayList<>();

    public Like addLike(Like like) {
        like.setBoard(this);
        this.likes.add(like);

        return like;
    }
}
