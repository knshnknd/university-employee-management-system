package ru.knshnknd.UniversityEmployeeManagementSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Training;
import ru.knshnknd.UniversityEmployeeManagementSystem.repositories.TrainingRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TrainingService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    public Training findONe(int id) {
        Optional<Training> foundTraining = trainingRepository.findTrainingById(id);
        return foundTraining.orElse(null);
    }

    public List<Training> searchByTitle(String query) {
        return trainingRepository.findTrainingsByTitleStartingWith(query);
    }

    @Transactional
    public void save(Training training) {
        trainingRepository.save(training);
    }

    @Transactional
    public void update(int id, Training updatedTraining) {
        Training trainingToBeUpdate = trainingRepository.findTrainingById(id).get();

        updatedTraining.setId(id);
        // чтобы не терялась связь при обновлении
        updatedTraining.setOwner(trainingToBeUpdate.getOwner());

        trainingRepository.save(updatedTraining);
    }

    @Transactional
    public void delete(int id) {
        trainingRepository.deleteTrainingById(id);
    }

    public Employee getTrainingOwner(int id) {
        return trainingRepository.findTrainingById(id).map(Training::getOwner).orElse(null);
    }
}
