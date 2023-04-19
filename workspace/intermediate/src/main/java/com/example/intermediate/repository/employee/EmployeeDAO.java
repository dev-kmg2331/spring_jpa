package com.example.intermediate.repository.employee;

import com.example.intermediate.entity.employee.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO {
    @PersistenceContext EntityManager em;

    // 추가
    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    // 조회
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    // 삭제
    public void delete(Employee employee) {
        em.remove(employee);
    }

    // 전체조회
    public List<Employee> findAll() {
        String query = "select e from Employee e";
        return em.createQuery(query, Employee.class).getResultList();
    }
}
