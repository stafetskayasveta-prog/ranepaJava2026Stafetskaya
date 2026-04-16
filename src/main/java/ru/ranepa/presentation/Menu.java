package ru.ranepa.presentation;

import ru.ranepa.model.Employee;
import ru.ranepa.service.HRMService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private HRMService service;

    public Menu(HRMService service) {
        this.service = service;
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n==== HRM MENU ====");
            System.out.println("1 - Show all employees");
            System.out.println("2 - Add employee");
            System.out.println("3 - Delete employee");
            System.out.println("4 - Find employee by ID");
            System.out.println("5 - Show statistics");
            System.out.println("6 - Exit");

            int input = scanner.nextInt();

            switch (input) {

                case 1 -> showAllEmployees();

                case 2 -> addEmployee();

                case 3 -> deleteEmployee();

                case 4 -> findEmployee();

                case 5 -> showStatistics();

                case 6 -> {
                    System.out.println("Program stopped...");
                    return;
                }

                default -> System.out.println("Unknown command");
            }
        }
    }

    private void showAllEmployees() {
        System.out.println(service.getAllEmployees());
    }

    private void addEmployee() {

        scanner.nextLine(); // очистка буфера после предыдущего nextInt

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter position:");
        String position = scanner.nextLine();

        System.out.println("Enter salary:");
        BigDecimal salary = scanner.nextBigDecimal();

        Employee employee = new Employee(
                null,
                name,
                position,
                salary,
                LocalDate.now()
        );

        service.addEmployee(employee);

        System.out.println("Employee added");
    }

    private void deleteEmployee() {

        System.out.println("Enter employee ID:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        boolean deleted = service.deleteEmployee(id);

        if (deleted) {
            System.out.println("Employee deleted");
        } else {
            System.out.println("Employee with ID " + id + " not found");
        }
    }

    private void findEmployee() {

        System.out.println("Enter employee ID:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            Employee employee = service.findEmployeeById(id);
            System.out.println("Employee found:");
            System.out.println(employee.getName() + " " + employee.getPosition());
        } catch (IllegalArgumentException e) {
            System.out.println("Employee with ID " + id + " not found");
        }
    }

    private void showStatistics() {

        System.out.println("Average salary:");
        System.out.println(service.getAverageSalary());

        System.out.println("Employee with highest salary:");
        System.out.println(service.getTopManager());
    }
}