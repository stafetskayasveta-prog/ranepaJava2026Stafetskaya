package ru.ranepa.repository;

import ru.ranepa.model.Employee;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeRepository {
    private HashMap<Long, Employee> employees = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public boolean save(Employee employee) {
        if (employee.getId() == null || employee.getId() == 0) {
            long newId = idGenerator.getAndIncrement();
            employee.setId(newId);
        } else {
            // Если ID уже задан (как в HrmApplication), обновляем генератор
            if (employee.getId() >= idGenerator.get()) {
                idGenerator.set(employee.getId() + 1);
            }
        }

        employees.put(employee.getId(), employee);
        return true;
    }

    public List<Employee> findAll() {
        return employees.values()
                .stream()
                .toList();
    }

    public Employee findById(long id) {
        if (!employees.containsKey(id)){
            throw new IllegalArgumentException("Такого сотрудника нет");
        }
        return employees.get(id);
    }

    public boolean delete(Long id) {
        if (!employees.containsKey(id)) {
            System.out.println("Такого сотрудника нет");
            return false;
        }
        employees.remove(id);
        return true;
    }
}