package com.example.advanced.repository.pet;

import com.example.advanced.entity.pet.Owner;
import com.example.advanced.entity.pet.Pet;
import com.example.advanced.enums.Gender;
import com.example.advanced.enums.PetDisease;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class OwnerDAOTest {

    @Autowired
    private OwnerDAO ownerDAO;

    @Test
    public void saveTest() {
        // given
        Owner owner = new Owner("강민구", "01012341234");
        owner.addPet(
                new Pet("쿠키", Gender.MALE, PetDisease.SICK),
                new Pet("쿠키", Gender.FEMALE, PetDisease.SUPERSICK)
        );

        // when
        Owner saved = ownerDAO.save(owner);

        // then
        assertThat(owner).isEqualTo(saved);
        assertThat(owner.getPets()).contains(owner.getPets().get(0));
    }

    @Test
    public void findByIdTest() {
        // given
        Owner owner = new Owner("강민구1", "01012341235");
        owner.addPet(
                new Pet("쿠키", Gender.MALE, PetDisease.SICK),
                new Pet("쿠키", Gender.FEMALE, PetDisease.SUPERSICK)
        );

        // when
        ownerDAO.save(owner);
        Optional<Owner> foundOwner = ownerDAO.findById(owner.getId());

        // then
        foundOwner.ifPresentOrElse(
                o -> {
                    assertThat(o).isEqualTo(owner);
                    assertThat(o.getPets().size()).isEqualTo(2);
                }
                , () -> log.info("조회된 회원 없음."));
    }

    @Test
    public void findAllTest() {
        // given
        Owner owner = new Owner("강민구2", "01012341236");
        owner.addPet(
                new Pet("쿠키", Gender.MALE, PetDisease.SICK)
        );

        // when
        ownerDAO.save(owner);
        List<Owner> owners = ownerDAO.findAll();

        // then
        assertThat(owners.size()).isGreaterThan(0);
    }

    @Test
    public void updateTest(){
        // given
        Owner owner = new Owner("강민구3", "01012341237");
        Pet pet = new Pet("쿠키", Gender.MALE, PetDisease.SICK);
        owner.addPet(pet);
        Pet addPet = new Pet("초코", Gender.FEMALE, PetDisease.SUPERSICK);

        // when
        ownerDAO.save(owner);
        owner.addPet(addPet);
        pet.setPetName("오태양");

        // then
        assertThat(owner.getPets().get(0).getPetName()).isEqualTo("오태양");
        assertThat(owner.getPets()).contains(addPet);
    }

    @Test
    public void deleteTest(){
        // given
        Owner owner = new Owner("강민구4", "01012341238");
        Pet pet = new Pet("쿠키", Gender.MALE, PetDisease.SICK);
        Pet addPet = new Pet("초코", Gender.FEMALE, PetDisease.SUPERSICK);
        owner.addPet(pet);

        // when
        ownerDAO.save(owner);
        ownerDAO.delete(owner);

        // then
    }
}