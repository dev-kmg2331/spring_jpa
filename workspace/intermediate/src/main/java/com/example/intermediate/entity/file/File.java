package com.example.intermediate.entity.file;

import com.example.intermediate.entity.audity.Period;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
/**
 * JOINED(조인 전략)
 * 부모 엔티티의 PK를 슈퍼키로 설정하고, 자식 엔티티의 PK를 서브키로 설정하는 전략.
 *
 * == 장점 ==
 * 정규화 방식
 *
 * == 단점 ==
 * 조회 시 JOIN으로 인해 성능 저하가 발생한다.
 * 복잡한 쿼리 작성 필요
 * INSERT 작성 시 쿼리 2번 실행
 * */
@Entity @Table(name = "TBL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends Period {
    @Id @GeneratedValue
    @Column(name = "FILE_ID")
    private Long id;

    @NotNull
    private String orgName;
    @NotNull
    private String uuid;
    @NotNull
    private String filePath;
    @NotNull
    private Long fileSize;

    public void setFile(String orgName, String uuid, String filePath, Long fileSize){
        this.orgName = orgName;
        this.uuid = uuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
