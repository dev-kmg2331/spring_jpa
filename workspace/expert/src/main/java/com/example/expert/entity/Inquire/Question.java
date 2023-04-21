package com.example.expert.entity.Inquire;

import com.example.expert.audit.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "TBL_QUESTION")
@Getter @Setter
public class Question extends Period {
    @Id @GeneratedValue
    private Long id;

    private String questionTitle;
    private String questionContent;

    /**
     * FK를 수정하는 주체는 연관관계의 주인이다.<br><br>
     * <p>
     * 연관관계의  주인을 설정할 때는 mappedBy를 이용해 설정하며
     * 연관관계의 주인으로 설정된 객체로 PK의 값을 관리한다.<br><br>
     * <p>
     * 단방향 2개로 양방향을 설계했을 경우 서로 FK를 수정 및 추가할 수 있다.
     * 그런데, 서로 수정을 하게 되면 양쪽 모두의 FK를 동기화해야 하기 때문에 번거롭고
     * 무결성에 위반될 수도 있다.<br>
     * 따라서, mappedBy를 사용하여 N쪽의 FK를 연관관계의 주인으로 설정해야 한다.<br>
     * mappedBy에 작성한 필드명은 RDB 진영에서 "_id"를 붙여 FK의 이름으로 사용된다.<br><br>
     */
    @OneToOne(
            mappedBy = "question",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Answer answer;

    /**
     * 연관관계의 주인이 아닌, 참조 엔티티로 FK를 추가하고자 할 때, <br>
     * NULL 값을 기존 FK값으로 변경하고자 사용한다.
     * */
    public void setAnswer(Answer answer) {
        // 전달받은 answer에 question 객체가 없다면
        if (answer.getQuestion() != this) {
            // 직접 question 객체를 넣어준다.
            answer.setQuestion(this);
        }
        this.answer = answer;
    }
}
