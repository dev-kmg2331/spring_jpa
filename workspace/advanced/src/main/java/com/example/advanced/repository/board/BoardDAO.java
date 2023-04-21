package com.example.advanced.repository.board;

import com.example.advanced.entity.board.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDAO {
    @PersistenceContext EntityManager em;

    public Board save(Board board){
        em.persist(board);
        return board;
    }

    public Optional<Board> findById(Long id) {
//        return Optional.ofNullable(em.find(Board.class, id));

        /**
         * 1:N 관계에서 한 방 쿼리를 작성하면 JOIN은 되지만
         * 여러 개가 조회되는 N의 특성상 SELECT 쿼리가 한 번 더 실행된다.
         * 따라서 1:N 관계일 경우에는 JOIN을 할 필요가 없다.
         * "select b from Board b inner join b.replies r where b.id = :id"
         *
         * 관계의 종류에 상관없이 참조 객체의 모든 정보를 한 번에 가져올 때에는
         * FETCH JOIN을 사용하면 된다.
         *
         * fetch 타입이 LAZY로 정해지면, 참조중인 객체를 조회할 때 마다 SELECT 문이 실행된다.
         * 성능이 저하될 수 있으므로 처음부터 한 방 쿼리로 가져와야 하지만
         * EAGER를 사용하면, JOIN이 필요 없는 엔티티까지 가져오므로
         * FETCH JOIN 을 사용해서 원하는 엔티티끼리만 정확히 가져와야 한다.
         *
         * JPQL로 엔티티를 조회할 경우, 1차 캐시에서 해당 쿼리를 처리하는 것은 매우 비효율적이고
         * 메모리 효율성이 낮기 때문에 바로 쿼리가 실행된 후
         * 가져온 결과를 1차 캐시와 비교한다.
         * */
        String query = "select b from Board b inner join fetch b.replies where b.id = :id";
        return Optional.ofNullable(
                em.createQuery(query, Board.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    public List<Board> findAll() {
        String query = "select b from Board b";
        return em.createQuery(query, Board.class).getResultList();
    }

    public void delete(Board board) {
        em.remove(board);
    }
}
