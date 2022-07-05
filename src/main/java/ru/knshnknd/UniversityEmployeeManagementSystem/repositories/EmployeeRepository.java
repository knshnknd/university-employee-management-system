package ru.knshnknd.UniversityEmployeeManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeById(int id);
    Optional<Employee> findEmployeeByFullName(String fullName);
    void deleteEmployeeById(int id);
 }
