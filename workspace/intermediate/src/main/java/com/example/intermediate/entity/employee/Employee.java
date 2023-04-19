package com.example.intermediate.entity.employee;

import com.example.intermediate.entity.audity.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * SINGLE_TABLE 전략(단일 테이블 전략)
 * 모든 자식 엔티티를 모아서 하나의 테이블로 생성하며, 구분 컬럼(DTYPE)을 추가하여 각 정보를 구분할 수 있도록 하는 전략.
 *
 * == 장점 ==
 * 조인을 사용할 필요 없이 조회 가능. (반 정규화)
 * 쿼리를 단순하게 작성하여 조회할 수 있다.
 *
 * == 단점 ==
 * 자식 엔티티의 필드는 @NotNull을 사용할 수 없다.
 * 테이블의 컬럼이 많아질 수록 조회 성능이 떨어질 수 있다.
 * */
@Entity @Table(name = "TBL_EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속관계 시 부모엔티티에 작성하며, 기본 전략은 SINGLE_TABLE 이다.
@DiscriminatorColumn(name = "DEPARTMENT") // SINGLE_TABLE 전략 시 구분(Discrimination)컬럼이 추가되며, 기본 컬럼명은 DTYPE이다.
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) @RequiredArgsConstructor
public class Employee extends Period {
    @Id @GeneratedValue
    @Column(name = "EMP_ID")
    private Long id;

    @NotNull @NonNull
    private String name;

    @NotNull @NonNull
    private LocalDate birth;

    private Integer career;

    public void setEmployee(String name, LocalDate birth, int career){
        this.name = name;
        this.birth = birth;
        this.career = career;
    }
}
