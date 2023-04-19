package com.example.intermediate.entity.file;

import com.example.intermediate.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "TBL_MEMBER_FILE")
//@PrimaryKeyJoinColumn(name = "MEMBER_FILE_ID")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFile extends File {
    private Integer isFirstImg;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberFile(String orgName, String uuid, String filePath, Long fileSize, Integer isFirstImg) {
        super.setFile(orgName, uuid, filePath, fileSize);
        this.isFirstImg = isFirstImg;
    }
}