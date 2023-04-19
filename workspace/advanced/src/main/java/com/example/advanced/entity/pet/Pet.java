package com.example.advanced.entity.pet;

import com.example.advanced.audit.Period;
import com.example.advanced.enums.Gender;
import com.example.advanced.enums.PetDisease;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "TBL_PET")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED) @ToString
public class Pet extends Period {
    @Id @GeneratedValue
    @Column
    private Long id;

    private String petName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private PetDisease petDisease;

    public Pet(String petName, Gender gender, PetDisease petDisease) {
        this.petName = petName;
        this.gender = gender;
        this.petDisease = petDisease;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
