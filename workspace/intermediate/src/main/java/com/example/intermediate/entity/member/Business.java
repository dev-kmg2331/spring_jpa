package com.example.intermediate.entity.member;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "TBL_BUSINESS")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business extends User{

    @NotNull @NonNull
    private String businessNumber;

    public Business (String identification, String password, String name, String address, String businessNumber) {
        super.setUser(identification, password, name, address);
        this.businessNumber = businessNumber;
    }
}
