package com.example.intermediate.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity @Table(name = "TBL_REVIEW_FILE")
//@PrimaryKeyJoinColumn(name = "REVIEW_FILE_ID")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile extends File {
    // 이미지 검사
    private Integer isImage;

    public ReviewFile(String orgName, String uuid, String filePath, Long fileSize, int isImage){
        super.setFile(orgName, uuid, filePath, fileSize);
        this.isImage = isImage;
    }
}
