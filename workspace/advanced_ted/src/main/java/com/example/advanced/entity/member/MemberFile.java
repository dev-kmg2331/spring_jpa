package com.example.advanced.entity.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "TBL_MEMBER_FILE")
@Getter @Setter @ToString(callSuper = true)
public class MemberFile extends File {
    @Enumerated
    private MemberFileType memberFileType;

    /**
     * CascadeType.REMOVE
     * 연관관계 주인 엔티티 삭제 시, 참조 엔티티도 삭제된다.
     * 1:1 매핑일 경우 삭제가 동시에 진행된다.
     * 하지만, N:1 매핑일 경우 참조 중인 엔티티가 더 있기 때문에 오류가 발생된다.
     * */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
