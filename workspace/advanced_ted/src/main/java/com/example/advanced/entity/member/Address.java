package com.example.advanced.entity.member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString @NoArgsConstructor @AllArgsConstructor
public class Address {
    private String memberAddress;
    private String memberAddressDetail;
    private String memberPostcode;
}
