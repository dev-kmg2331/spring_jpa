package com.example.expert.entity.board;

import com.example.expert.audit.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "TBL_LIKE")
@Getter @Setter
public class Like extends Period {
    @Id @GeneratedValue
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Board board;
}
