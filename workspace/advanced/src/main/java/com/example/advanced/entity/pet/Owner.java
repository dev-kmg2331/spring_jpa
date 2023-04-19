package com.example.advanced.entity.pet;

import com.example.advanced.audit.Period;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_OWNER")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED) @ToString
public class Owner extends Period {
    @Id @GeneratedValue
    @Column
    private Long id;

    private String ownerName;

    @Column(unique = true)
    private String ownerPhone;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Pet> pets = new ArrayList<>();

    public Owner(String ownerName, String ownerPhone) {
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public void addPet(Pet... pets) {
        for (Pet pet : pets) {
            this.pets.add(pet);
        }
    }
}
