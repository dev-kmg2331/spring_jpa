package com.example.intermediate.entity.computer;

import com.example.intermediate.entity.audity.Period;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "TBL_COMPUTER")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Computer extends Period {
    @Id @GeneratedValue
    @Column(name = "COMPUTER_ID")
    private Long id;

    @NotNull
    private Integer screen;
    @NotNull
    private String brand;
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private LocalDate releaseDate;

    @Embedded
    @NotNull
    private Hardware hardWare;

    public Computer(Integer screen, String brand, String name, Integer price, LocalDate releaseDate, Hardware hardWare) {
        this.screen = screen;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
        this.hardWare = hardWare;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
