package com.example.basic.repository;

import com.example.basic.domain.entity.Member;
import com.example.basic.domain.entity.SuperCar;
import com.example.basic.type.MemberType;
import com.example.basic.type.SuperCarBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class SuperCarDAOTest {

    @Autowired
    SuperCarDAO superCarDAO;

    @Test
    public void saveTest() {
        // given
        SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "따릉이", "파랑색", 10000);

        // when
        superCarDAO.save(superCar);

        // then
    }

    @Test
    public void updateTest() {
        // given
        SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "따릉이", "파랑색", 10000);

        // when
        superCarDAO.save(superCar).setName("부릉이");

        // then
        assertThat(superCar.getName()).isEqualTo("부릉이");
    }

    @Test
    public void findByIdTest() {
        // given
        SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "따릉이", "파랑색", 10000);
        superCar = superCarDAO.save(superCar);

        // when
        Optional<SuperCar> optionalSuperCar = superCarDAO.findById(superCar.getId());

        // then
        assertThat(optionalSuperCar.isPresent()).isTrue();
    }

    @Test
    public void deleteTest() {
        // given
        SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "따릉이", "파랑색", 10000);
        superCar = superCarDAO.save(superCar);

        // when
        superCarDAO.delete(superCar);
        Optional<SuperCar> optionalSuperCar = superCarDAO.findById(superCar.getId());

        // then
    }

    @Test
    public void findAllTest() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(i));
            superCarDAO.save(superCar);
        }

        // when
        List<SuperCar> foundCars = superCarDAO.findAll();

        // then
        foundCars.stream().map(SuperCar::getPrice).map(String::valueOf).forEach(log::info);
    }

    @Test
    public void findAllWithPagingTest() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(i));
            superCarDAO.save(superCar);
        }

        // when
        List<SuperCar> foundCars = superCarDAO.findAllWithPaging(0, 10);

        // then
        foundCars.stream().forEach(car -> log.info(car.getId() + " : " + car.getName()));
    }

    @Test
    public void findAllWithReleaseDate() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(i).withNano(0));
            superCarDAO.save(superCar);
        }

        // when
        List<SuperCar> foundCars = superCarDAO.findAllByReleaseDate(LocalDateTime.now().minusMonths(3));

        // then
        foundCars.stream().map(SuperCar::getPrice).map(String::valueOf).forEach(log::info);
    }

    @Test
    public void findAllByReleaseDateBetweenTest() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(i).withNano(0));
            superCarDAO.save(superCar);
        }

        // when
        List<SuperCar> foundCars = superCarDAO.findAllByReleaseDateBetween(LocalDateTime.now().minusMonths(10), LocalDateTime.now());

        // then
        foundCars.stream().forEach(car -> log.info(car.getName() + " : " + car.getReleaseDate()));
    }

    @Test
    public void findAllWithNameAndPriceTest() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(i));
            superCarDAO.save(superCar);
        }

        // when
        List<SuperCar> foundCars = superCarDAO.findAllWithNameAndPrice("부릉이1", 100000000);

        // then
        foundCars.stream().forEach(car -> log.info(car.getName() + " : " + car.getPrice()));
    }

    @Test
    public void deleteByPriceGreaterThanEqual() {
        // given
        int price = 30000000;
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(20));
            superCarDAO.save(superCar);
        }

        // when
        superCarDAO.deleteByPriceGreaterThanEqual(price);

        // then
        superCarDAO.findAll().stream().map(SuperCar::getPrice).forEach(p -> log.info("가격 : " + p));
        superCarDAO.findAll().stream().map(SuperCar::getPrice).forEach(p -> assertThat(p).isLessThan(price));
    }

    @Test
    public void updateByReleaseDateTest() {
        // given
        for (int i = 0; i < 50; i++) {
            SuperCar superCar = new SuperCar(SuperCarBrand.BMW, "부릉이" + i, "빨간색", i * 1000000);
            superCar.setReleaseDate(LocalDateTime.now().minusMonths(20));
            superCarDAO.save(superCar);
        }

        // when
//        List<Integer> originalPrices = superCarDAO
//                .findAllByReleaseDate(LocalDateTime.now().minusMonths(20))
//                .stream().map(SuperCar::getPrice)
//                .collect(Collectors.toList());

        superCarDAO.updateByReleaseDate(LocalDateTime.now().minusMonths(20), 1.1);


        // then
        superCarDAO.findAllByReleaseDate(LocalDateTime.now().minusMonths(20))
                .stream().map(SuperCar::getPrice)
//                .collect(Collectors.toList());
                .forEach(price -> log.info("가격 : " + price));
    }
}