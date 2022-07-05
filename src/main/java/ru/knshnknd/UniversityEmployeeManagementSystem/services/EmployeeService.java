package ru.knshnknd.UniversityEmployeeManagementSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Department;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Training;
import ru.knshnknd.UniversityEmployeeManagementSystem.repositories.EmployeeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(int id) {
        Optional<Employee> foundEmployee = employeeRepository.findEmployeeById(id);
        return foundEmployee.orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(int id, Employee updateEmployee) {
        Employee employeeToBeUpdate = employeeRepository.findEmployeeById(id).get();

        updateEmployee.setId(id);
        // чтобы не терялась связь при обновлении
        updateEmployee.setDepartment(employeeToBeUpdate.getDepartment());

        employeeRepository.save(updateEmployee);
    }

    @Transactional
    public void delete(int id) {
        employeeRepository.deleteEmployeeById(id);
    }

    public Optional<Employee> getEmployeeByFullName(String fullName) {
        return employeeRepository.findEmployeeByFullName(fullName);
    }

    public Department getDepartmentIdOfEmployee(int id) {
        return employeeRepository.findEmployeeById(id).map(Employee::getDepartment).orElse(null);
    }

    public List<Training> getTrainingsByEmployeeId(int id) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(id);
        if (employee.isPresent()) {
            return employee.get().getTrainings();
        } else {
            return Collections.emptyList();
        }
    }
}
