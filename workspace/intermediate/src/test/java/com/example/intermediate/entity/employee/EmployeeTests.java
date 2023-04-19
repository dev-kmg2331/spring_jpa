package com.example.intermediate.entity.employee;

import com.example.intermediate.repository.employee.EmployeeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class EmployeeTests {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void saveTest() {
        // given
        Employee employee = new Developer(
                "강민구"
                , LocalDate.of(1998, 1, 30)
                , 10, 10, 10);

        // when
        Employee savedEmp = employeeDAO.save(employee);

        // then
        assertThat(employee).isEqualTo(savedEmp);
    }

    @Test
    public void findByIdTest() {
        // given
        Employee employee = new Developer(
                "강민구"
                , LocalDate.of(1998, 1, 30)
                , 10, 10, 10);

        // when
        Employee savedEmp = employeeDAO.save(employee);

        // then
        assertThat(employeeDAO.findById(savedEmp.getId()).isPresent()).isTrue();
    }
}