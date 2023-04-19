package com.example.advanced.entity.board;

import com.example.advanced.audit.Period;
import com.example.advanced.entity.user.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_BOARD")
@Getter @Setter @ToString(exclude = "member") @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period {
    @Id @GeneratedValue
    @Column
    private Long id;

    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // fetch
    // 연관관계를 맷고 있는 경우,
    // 조회 시 객체를 가져오는 방법을 기술하는 옵션이다.
    // - EAGER : 모든 연관관계 객체를 JOIN하여 한 방 쿼리로 가져온다.
    // - LAZY : 첫 객체만 SELECT하고, 연관관계 객체를 사용할 때 쿼리가 다시 발생된다.
    // 복잡한 연관 관계 속에서 EAGER를 사용하면 불필요한 JOIN이 발생하기 때문에 성능이슈가 발생할 수 있다.
    // 따라서 실무에서는 LAZY로 설정해야 하며, 한 방 쿼리가 필요할 때에는 테이블끼리만 JOIN하여 사용한다.

    // cascade(영속성 전이)
    // 엔티티가 영속상태로 전환될 때 참조 엔티티도 영속상태로 같이 전환되고,
    // 삭제상태로 전환될 때도 참조 엔티티까지 삭제상태로 전환된다.
    // 즉, 연관관계 객체에도 영속상태가 전이되는 옵션이다.

    // @OneToMany 일 경우 fetch의 기본 옵션이 LAZY이다.
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "BOARD_ID")
    private List<Reply> replies = new ArrayList<>();

    public Board(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }
}
