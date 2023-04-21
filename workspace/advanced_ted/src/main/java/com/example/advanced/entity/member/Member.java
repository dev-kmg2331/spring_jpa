package com.example.advanced.entity.member;

import com.example.advanced.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_MEMBER") @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Period {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true) @NotNull
    private String memberId;
    @NotNull
    private String memberPassword;

    @Column(unique = true) @NotNull
    private String memberEmail;

    @Embedded
    private Address address;

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private List<MemberFile> memberFiles = new ArrayList<>();

    public Member(String memberId, String memberPassword, String memberEmail, Address address) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.address = address;
    }
}

















