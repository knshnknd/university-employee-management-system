package ru.knshnknd.UniversityEmployeeManagementSystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[а-яА-ЯёЁ\\s]+$", message = "Можно использовать только буквы русского алфавита.")
    @NotBlank(message = "Название подразделения не может быть пустым")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getEmployeesNumber() {
        return employees.size();
    }

    public int getAllHoursOfAllEmployees() {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getHours();
        }
        return sum;
    }

    public int getNumberOfTrainingsOfAllEmployees() {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getTrainingsNumber();
        }
        return sum;
    }
}
