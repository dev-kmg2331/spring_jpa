package com.example.intermediate.entity.member;

import com.example.intermediate.entity.file.MemberFile;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_MEMBER")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Member extends User {

    @NotNull
    private String socialNumber;

    @OneToMany(mappedBy = "member")
    private List<MemberFile> memberFiles = new ArrayList<>();

    public Member(String identification, String password, String name, String address, String socialNumber) {
        super.setUser(identification, password, name, address);
        this.socialNumber = socialNumber;
    }

    public void addMemberFiles(MemberFile ...memberFiles) {
        for (MemberFile file : memberFiles) {
            file.setMember(this);
            this.memberFiles.add(file);
        }
    }
}
