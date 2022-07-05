package ru.knshnknd.UniversityEmployeeManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Department;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.DepartmentService;
import ru.knshnknd.UniversityEmployeeManagementSystem.util.DepartmentValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentValidator departmentValidator;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentValidator departmentValidator) {
        this.departmentService = departmentService;
        this.departmentValidator = departmentValidator;
    }

    @GetMapping()
    public String showAllDepartments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments/index";
    }

    @GetMapping("/{id}")
    public String showDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", departmentService.findOne(id));
        model.addAttribute("employees", departmentService.getEmployeesByDepartmentId(id));
        return "departments/show";
    }

    @GetMapping("/new")
    public String newDepartment(@ModelAttribute("department") Department department) {
        return "departments/new";
    }

    @PostMapping()
    public String createDepartment(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult) {
        departmentValidator.validate(department, bindingResult);

        if (bindingResult.hasErrors()) {
            return "departments/new";
        }

        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findOne(id));
        return "departments/edit";
    }

    @PatchMapping("/{id}")
    public String updateDepartment(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "departments/edit";
        }
        departmentService.update(id, department);
        return "redirect:/departments";
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
