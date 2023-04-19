package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class SuperCarDAO {

    @PersistenceContext
    EntityManager em;

    public SuperCar save(SuperCar superCar) {
        em.persist(superCar);
        em.flush();
        return superCar;
    }

    public Optional<SuperCar> findById(Long id) {
        return Optional.ofNullable(em.find(SuperCar.class, id));
    }

    public void delete(SuperCar superCar) {
        em.remove(superCar);
    }

    //    전체 조회
    public List<SuperCar> findAll() {
        String query = "select s from SuperCar s";
        return em.createQuery(query, SuperCar.class).getResultList();
    }

    //    전체 조회 페이징
    public List<SuperCar> findAllWithPaging(int startRow, int rowCount) {
        String query = "select s from SuperCar s order by s.id desc";
        return em.createQuery(query, SuperCar.class)
                .setFirstResult(startRow)
                .setMaxResults(rowCount)
                .getResultList();
    }

    //    특정 날짜 조회
    public List<SuperCar> findAllByReleaseDate(LocalDateTime releaseDate) {
        String query = "select s from SuperCar s where function('TO_CHAR', s.releaseDate, 'yyyymmdd') = :releaseDate";
        return em.createQuery(query, SuperCar.class)
                .setParameter("releaseDate", releaseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .getResultList();
    }

    // 특정 출시 기간 조회
    public List<SuperCar> findAllByReleaseDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        String query = "select s from SuperCar s where s.releaseDate between :startDate and :endDate";
        return em.createQuery(query, SuperCar.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    // 특정 이름 가격 조회
    public List<SuperCar> findAllWithNameAndPrice(String name, int price) {
        String query = "select s from SuperCar s where s.name like concat('%', :name, '%') and s.price <= :price";
        return em.createQuery(query, SuperCar.class)
                .setParameter("name", name)
                .setParameter("price", price)
                .getResultList();
    }

    // 4천만원이 넘는 가격대의 자동차 삭제
    public void deleteByPriceGreaterThanEqual(int price) {
        String query = "delete from SuperCar s where s.price >= :price";
        em.createQuery(query).setParameter("price", price).executeUpdate();
    }

    //     특정 출시일의 자동차 가격을 10% 상승
    public void updateByReleaseDate(LocalDateTime releaseDate, double raise) {
        // JPQL SET절 또는 WHERE절에서는 좌항의 필드 자료형과 우항의 파라미터의 값이 동일한 지 검사된다.
        // 만약 좌항의 필드가 long 타입이고 우항의 파라미터가 double 타입이라면, IllegalArgumentException이 발생한다.
        // 따라서 항상 좌항의 필드 타입과 우항의 파라미터 타입이 동일해야 한다.
        // ※ 우항에서 연산을 위해 ()괄호 연산자를 사용하게 되면 알 수 없는 타입으로 검사되기 때문에 연산은 모두 JAVA 코드에서 진행한다.
        String query = "update SuperCar s set s.price = s.price * :raise " +
                "where function('TO_CHAR', s.releaseDate, 'yyyymmdd') = :releaseDate ";
        em.createQuery(query)
                .setParameter("raise", raise)
                .setParameter("releaseDate", releaseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .executeUpdate();
        em.clear();
    }
}
