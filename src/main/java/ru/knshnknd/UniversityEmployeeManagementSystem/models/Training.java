package ru.knshnknd.UniversityEmployeeManagementSystem.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee owner;

    @NotBlank(message = "Название повышения квалификации не должно быть пустым.")
    private String title;

    @NotBlank(message = "Номер удостоверения повышения квалификации не должен быть пустым.")
    private String certificateNumber;

    @NotBlank(message = "Место обучения не должно быть пустым.")
    private String placeOfStudy;

    @Min(value = 1, message = "Нельзя вводить меньше 1 часа.")
    @Max(value = 1500, message = "Нельзя вводить больше 9000 часов.")
    private int hours;

    public Training() {
    }

    public Training(Employee owner, String title, String certificateNumber, String placeOfStudy, int hours) {
        this.owner = owner;
        this.title = title;
        this.certificateNumber = certificateNumber;
        this.placeOfStudy = placeOfStudy;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPlaceOfStudy() {
        return placeOfStudy;
    }

    public void setPlaceOfStudy(String placeOfStudy) {
        this.placeOfStudy = placeOfStudy;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
