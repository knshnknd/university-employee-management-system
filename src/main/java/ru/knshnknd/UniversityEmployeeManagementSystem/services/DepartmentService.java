package ru.knshnknd.UniversityEmployeeManagementSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Department;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.repositories.DepartmentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findOne(int id) {
        Optional<Department> foundDepartment= departmentRepository.findDepartmentById(id);
        return foundDepartment.orElse(null);
    }

    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findDepartmentByName(name);
    }

    @Transactional
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void update(int id, Department updatedDepartment) {
        updatedDepartment.setId(id);
        departmentRepository.save(updatedDepartment);
    }

    @Transactional
    public void delete(int id) {
        departmentRepository.deleteDepartmentById(id);
    }

    public List<Employee> getEmployeesByDepartmentId(int id) {
        Optional<Department> department = departmentRepository.findDepartmentById(id);
        if (department.isPresent()) {
            return department.get().getEmployees();
        } else {
            return Collections.emptyList();
        }
    }
}
