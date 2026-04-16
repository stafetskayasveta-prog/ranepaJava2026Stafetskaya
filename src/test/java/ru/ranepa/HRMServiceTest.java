package ru.ranepa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;
import ru.ranepa.service.HRMService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HRMServiceTest {

    @Test
    public void shouldCalculateAverageSalary() {

        // Given
        EmployeeRepository repository = new EmployeeRepository();
        HRMService service = new HRMService(repository);

        repository.save(new Employee(
                1L,
                "Ivan",
                "Dev",
                new BigDecimal("100"),
                LocalDate.now()
        ));

        repository.save(new Employee(
                2L,
                "Anna",
                "QA",
                new BigDecimal("200"),
                LocalDate.now()
        ));

        repository.save(new Employee(
                3L,
                "Petr",
                "Manager",
                new BigDecimal("300"),
                LocalDate.now()
        ));

        // When
        double average = service.getAverageSalary();

        // Then
        assertEquals(200, average);
    }

    @Test
    public void shouldFindTopManager() {

        // Given
        EmployeeRepository repository = new EmployeeRepository();
        HRMService service = new HRMService(repository);

        repository.save(new Employee(
                1L,
                "Ivan",
                "Dev",
                new BigDecimal("100"),
                LocalDate.now()
        ));

        repository.save(new Employee(
                2L,
                "Anna",
                "Manager",
                new BigDecimal("500"),
                LocalDate.now()
        ));

        // When
        Employee topManager = service.getTopManager();

        // Then
        assertEquals("Anna", topManager.getName());
    }

    @Test
    public void shouldThrowExceptionWhenNoEmployees() {

        // Given
        EmployeeRepository repository = new EmployeeRepository();
        HRMService service = new HRMService(repository);

        // Then
        assertThrows(Exception.class, () -> {
            service.getTopManager();
        });
    }
}