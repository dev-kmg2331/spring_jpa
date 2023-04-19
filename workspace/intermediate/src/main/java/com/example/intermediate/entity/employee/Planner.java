package com.example.intermediate.entity.employee;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "TBL_PLANNER")
@DynamicInsert // 필드에 NULL이 들어가 있다면 INSERT 쿼리에 해당 필드를 제외해준다.
@DynamicUpdate // 필드에 NULL이 들어가 있다면 UPDATE SET 쿼리에 해당 필드를 제외해준다.
@DiscriminatorValue("PLN")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Planner extends Employee {

    @Column(name = "OA_LEVEL")
    private Integer level;

    // 컬럼의 초기값 설정. @DynamicInsert 를 통해 제외된 필드는 설정한 Default 값이 들어간다.
    @ColumnDefault(value = "0")
    private Integer clientCount;

}
