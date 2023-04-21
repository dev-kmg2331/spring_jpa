package com.example.advanced.repository.pet;

import com.example.advanced.entity.pet.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PetDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    추가
    public Pet save(Pet pet){
        entityManager.persist(pet);
        return pet;
    }

    //    조회
    public Optional<Pet> findById(Long id){
//        return Optional.ofNullable(entityManager.find(Pet.class, id));
        String query = "select p from Pet p join fetch p.owner where p.id = :id";
        return Optional.ofNullable(
                entityManager
                        .createQuery(query, Pet.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    //    전체 조회
    public List<Pet> findAll(){
//        String query = "select p from Pet p join fetch p.owner";

        /**
         * LAZY의 경우 참조중인 엔티티를 원본이 아닌 프록시(대리인)로 받아온다.
         * 이 때 참조중인 엔티티를 사용하는 순간 SELECT문이 실행되며,
         * 사용하지 않을경우 프록시로만 존재하기 떄문에 SELECT문은 처음부터 실행되지 않는다.
         *
         * fetch join을 사용하면 참조중인 엔티티에 원본 객체를 담아놓기 때문에
         * 사용할 때 마다 SELECT문이 실행되지 않는다.
         * */
        String query = "select p from Pet p inner join p.owner";
        return entityManager.createQuery(query, Pet.class).getResultList();
    }

    //    삭제
    public void delete(Pet pet){
        entityManager.remove(pet);
    }
}







