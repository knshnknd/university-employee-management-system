package ru.knshnknd.UniversityEmployeeManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Training;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Optional<Training> findTrainingById(int id);
    List<Training> findTrainingsByOwner(Employee employee);
    void deleteTrainingById(int id);
    List<Training> findTrainingsByTitleStartingWith(String titleStarting);
}
