package com.example.advanced.entity.user;

import com.example.advanced.entity.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_MEMBER") @ToString(callSuper = true)
@Getter @NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member extends User {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Board> boards = new ArrayList<>();

    public void addBoards(Board ...boards) {
        for (Board board : boards) {
            this.boards.add(board);
            board.setMember(this);
        }
    }
}
