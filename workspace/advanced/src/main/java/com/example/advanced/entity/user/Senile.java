package com.example.advanced.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "TBL_SENILE")
public class Senile {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
}
