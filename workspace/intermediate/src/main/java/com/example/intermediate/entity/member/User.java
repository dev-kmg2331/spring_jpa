package com.example.intermediate.entity.member;

import com.example.intermediate.entity.audity.Period;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
/**
 * TABLE_PER_CLASS(엔티티 당 한 개 테이블 전략)
 * 부모 엔티티와 자식 엔티티마다 테이블을 생성하는 전략, 실무에서 사용을 권장하지 않는다.
 *
 * == 장점 ==
 * 자식 엔티티의 필드는 @NotNull을 사용할 수 있다.
 *
 * == 단점 ==
 * 자식 테이브끼리 JOIN시 겹치는 컬럼으로 인해 조회 성능이 떨어지고 쿼리가 복잡해진다.
 * */
@Entity @Table(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class User extends Period { // 부모 클래스를 단독으로 사용하지 않는다면, abstract로 만든다.
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @NotNull @NonNull
    @Column(unique = true)
    private String identification;
    @NotNull @NonNull
    private String password;
    @NotNull @NonNull
    private String name;
    private String address;

    public void setUser(String identification, String password, String name, String address) {
        this.identification = identification;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public void changeProfile(String password, String name, String address) {
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
