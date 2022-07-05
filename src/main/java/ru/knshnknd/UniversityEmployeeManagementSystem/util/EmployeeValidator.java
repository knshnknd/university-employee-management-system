package ru.knshnknd.UniversityEmployeeManagementSystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.EmployeeService;

@Component
public class EmployeeValidator implements Validator {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        if(employeeService.getEmployeeByFullName(employee.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует!");
        }
    }
}
