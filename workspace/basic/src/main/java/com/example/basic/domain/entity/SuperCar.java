package com.example.basic.domain.entity;

import com.example.basic.type.SuperCarBrand;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SUPER_CAR")
@Getter
public class SuperCar {

    protected SuperCar() {
    }

    public SuperCar(SuperCarBrand brand, String name,
                    String color, Integer price) {
        this.brand = brand;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Id
    @GeneratedValue
    @Column(name = "SUPER_CAR_ID")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SuperCarBrand brand;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Integer price;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime releaseDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
