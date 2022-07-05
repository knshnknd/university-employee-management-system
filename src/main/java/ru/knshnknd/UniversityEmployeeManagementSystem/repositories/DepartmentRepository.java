package ru.knshnknd.UniversityEmployeeManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Department;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Training;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findDepartmentById(int id);
    Optional<Department> findDepartmentByName(String name);
    void deleteDepartmentById(int id);
}
