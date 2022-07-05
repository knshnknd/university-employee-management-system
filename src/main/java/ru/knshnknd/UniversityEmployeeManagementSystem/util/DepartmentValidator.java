package ru.knshnknd.UniversityEmployeeManagementSystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Department;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.DepartmentService;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.EmployeeService;

@Component
public class DepartmentValidator implements Validator {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        if(departmentService.getDepartmentByName(department.getName()).isPresent()) {
            errors.rejectValue("name", "", "Подразделение с таким наименованием уже существует!");
        }
    }
}
