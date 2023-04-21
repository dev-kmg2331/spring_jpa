package com.example.expert.entity.Inquire;

import com.example.expert.audit.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "TBL_ANSWER")
@Getter @Setter
public class Answer extends Period {
    @Id @GeneratedValue
    private Long id;

    private String answerContent;

    /**
     *
     * - @JoinColumn 은 해당 엔티티가 연관관계의 주인임을 명시한다.<br>
     * mappedBy는 fk의 관리를 위임하는 속성이며<br>
     * - @JoinColumn과 mappedBy는 서로의 역할이 다르다.<br><br>
     *
     * - @joinColumn에 name 속성을 주지 않아도 FK 컬럼명은<br>
     * 연관관계 주인의 필드명을 참조하여 뒤에 "_id"가 붙어 생성된다.
     *
     * */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Question question;
}
