package com.example.advanced.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity @Table(name = "TBL_WELFARE")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Welfare extends User {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "WELFARE_ID")
    private List<Senile> seinor = new ArrayList<>();
}
