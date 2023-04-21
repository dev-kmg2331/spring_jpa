package com.example.advanced.entity.hospital;

import com.example.advanced.repository.hospital.PetDAO;
import com.example.advanced.type.GenderType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class HospitalTests {
    @Autowired
    private PetDAO petDAO;

    @Test
    public void saveTest(){
        String[] arDisease = {"감기", "배탈", "방광염", "설사", "피부병"};

        for (int i=0; i<10; i++){
            Owner owner = new Owner();
            Pet pet = new Pet();

            owner.setOwnerName("정세인");
            owner.setOwnerPhone("0101234123" + i);

            pet.setPetName("뽀삐" + (i+1));
            pet.setPetGender(GenderType.MALE);
            pet.setPetDisease(arDisease[new Random().nextInt(arDisease.length)]);
            pet.setOwner(owner);

            petDAO.save(pet);
        }
    }

    @Test
    public void findById(){
//        petDAO.findById(1L).map(Pet::toString).ifPresent(log::info);

//        LAZY의 경우 참조중인 엔티티를 원본이 아닌 프록시(대리인)로 받아온다.
//        이 때 참조중인 엔티티를 사용하는 순간 SELECT문이 실행되며,
//        사용하지 않을 경우 프록시로만 존재하기 때문에 SELECT문은 처음부터 실행되지 않는다.
//        fetch join을 사용하면 참조중인 엔티티에 원본 객체를 담아놓기 때문에
//        사용할 때 마다 SELECT문이 실행되지 않는다.
        petDAO.findById(1L).ifPresent(pet -> log.info(pet.getOwner().getClass().getName()));
    }

    @Test
    public void findAll(){
        petDAO.findAll().stream().map(Pet::toString).forEach(log::info);
    }



}






















