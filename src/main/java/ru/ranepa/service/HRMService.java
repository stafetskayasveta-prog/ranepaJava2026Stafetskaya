package ru.ranepa.service;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HRMService {

    private EmployeeRepository repository;

    public HRMService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public double getAverageSalary() {
        return repository.findAll()
                .stream()
                .mapToDouble(e -> e.getSalary().doubleValue())
                .average()
                .orElseThrow();
    }

    public String getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(e ->
                        "ID: " + e.getId() +
                                " | Name: " + e.getName() +
                                " | Position: " + e.getPosition() +
                                " | Salary: " + e.getSalary()
                )
                .collect(Collectors.joining("\n"));
    }

    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    public boolean deleteEmployee(Long id) {
        return repository.delete(id);
    }

    public Employee findEmployeeById(Long id) {
        return repository.findById(id);
    }
    public Employee getTopManager() {
        return repository.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }
    public String getStatistics() {
        return "Average salary: " + getAverageSalary() +
                "\nTop manager: " + getTopManager().getName();
    }
}