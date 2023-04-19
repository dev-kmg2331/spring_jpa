package com.example.basic.domain.entity;

import com.example.basic.type.MemberType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
/*@ToString*//*(exclude = "id")*/
@Table(name = "TBL_MEMBER")
public class Member {

    public Member() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull // JAVA에서의 Validation(DB와 상관 없음)
    private String memberName;

    @NotNull
    @Column(unique = true, nullable = false) // DBMS에서의 NOT NULL 제약조건 추가
    private String memberEmail;

    @NotNull
    private String memberPassword;

    @NotNull
    private int memberAge;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
}
