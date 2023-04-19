package com.example.advanced.entity.user;

import com.example.advanced.audit.Period;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "TBL_USER")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Period {
    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String userPassword;

    @Embedded
    private Address address;


}
