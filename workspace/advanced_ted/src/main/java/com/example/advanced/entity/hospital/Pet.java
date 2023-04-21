package com.example.advanced.entity.hospital;

import com.example.advanced.audit.Period;
import com.example.advanced.type.GenderType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_PET")
public class Pet extends Period {
    @Id @GeneratedValue
    private Long id;
    private String petName;
    @Enumerated(EnumType.STRING)
    @NotNull private GenderType petGender;
    @NotNull private String petDisease;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}




























