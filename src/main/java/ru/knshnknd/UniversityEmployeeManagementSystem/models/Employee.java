package ru.knshnknd.UniversityEmployeeManagementSystem.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "ФИО не должно быть пустым.")
    @Pattern(regexp = "^[а-яА-ЯёЁ\\s]+$", message = "Можно использовать только буквы русского алфавита.")
    private String fullName;

    @NotBlank(message = "Должность не должна быть пустой.")
    @Pattern(regexp = "^[а-яА-ЯёЁ\\s]+$", message = "Можно использовать только буквы русского алфавита.")
    private String rank;

    @Min(value = 1, message = "Нельзя вводить меньше 1 часа.")
    @Max(value = 1500, message = "Нельзя вводить больше 1500 часов.")
    private int hours;

    @NotBlank(message = "Электронная почта не должна быть пустой.")
    @Size(min = 2, max = 150, message = "Длина адреса электронной почты должна быть больше 2 знаков и меньше 150 знаков.")
    @Email(message = "Неверный формат электронной почты. Введите по образцу: text@test.com")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "owner")
    private List<Training> trainings;

    public Employee() {
    }

    public Employee(String fullName, String rank, int hours, String email,
                    Department department, List<Training> trainings) {
        this.fullName = fullName;
        this.rank = rank;
        this.hours = hours;
        this.email = email;
        this.department = department;
        this.trainings = trainings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String departmentName) {
        this.rank = departmentName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int wageRate) {
        this.hours = wageRate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public int getTrainingsNumber() {
        return trainings.size();
    }
}
