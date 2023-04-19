package com.example.intermediate.entity.computer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Embeddable
 * 상속 관계가 아닌 필드의 그룹화(모듈화)를 목적으로 사용한다.
 * 그룹화된 필드를 따로 사용하지 않고 한 번에 사용하는 목적으로 설계한다.
 * */
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hardware {
    private Integer ram;
    private Integer ssd;
    private String gpu;
    private String processor;

    public void updateHardware() {

    }
}
