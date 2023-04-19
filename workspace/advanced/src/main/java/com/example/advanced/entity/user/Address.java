package com.example.advanced.entity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String first;
    private String detail;
    private String mailNumber;
}
