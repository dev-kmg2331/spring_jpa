package com.example.advanced.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_BUSINESS")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business extends User {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "BUSINESS_ID")
    private List<Item> items = new ArrayList<>();
}
