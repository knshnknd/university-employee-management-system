package ru.knshnknd.UniversityEmployeeManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Training;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.EmployeeService;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.TrainingService;

import javax.validation.Valid;

@Controller
@RequestMapping("/trainings")
public class TrainingController {
    private final EmployeeService employeeService;
    private final TrainingService trainingService;

    @Autowired
    public TrainingController(EmployeeService employeeService, TrainingService trainingService) {
        this.employeeService = employeeService;
        this.trainingService = trainingService;
    }

    @GetMapping()
    public String showAllTrainings(Model model) {
        model.addAttribute("trainings", trainingService.findAll());
        return "trainings/index";
    }

    @GetMapping("/{id}")
    public String showTraining(@PathVariable("id") int id, Model model,
                               @ModelAttribute("training") Training training) {
        model.addAttribute("training", trainingService.findONe(id));
        return "trainings/show";
    }

    @GetMapping("/new")
    public String newTraining(Model model, @ModelAttribute("training") Training training) {
        model.addAttribute("employees", employeeService.findAll());
        return "trainings/new";
    }

    @PostMapping()
    public String createTraining(Model model, @ModelAttribute("training") @Valid Training training,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "trainings/new";
        }
        trainingService.save(training);
        return "redirect:/trainings";
    }

    @GetMapping("/{id}/edit")
    public String editTraining(Model model, @PathVariable("id") int id) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("training", trainingService.findONe(id));
        return "trainings/edit";
    }

    @PatchMapping("/{id}")
    public String updateTraining(Model model, @ModelAttribute("training") @Valid Training training, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "trainings/edit";
        }

        trainingService.update(id, training);
        return "redirect:/trainings";
    }

    @DeleteMapping("/{id}")
    public String deleteTraining(@PathVariable("id") int id) {
        trainingService.delete(id);
        return "redirect:/trainings";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "trainings/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("trainings", trainingService.searchByTitle(query));
        return "trainings/search";
    }
}
