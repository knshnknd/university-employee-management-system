package ru.knshnknd.UniversityEmployeeManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knshnknd.UniversityEmployeeManagementSystem.models.Employee;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.DepartmentService;
import ru.knshnknd.UniversityEmployeeManagementSystem.services.EmployeeService;
import ru.knshnknd.UniversityEmployeeManagementSystem.util.EmployeeValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    private final EmployeeValidator employeeValidator;

    @Autowired
    public EmployeesController(DepartmentService departmentService,
                               EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping()
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String showEmployee(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        model.addAttribute("trainings", employeeService.getTrainingsByEmployeeId(id));
        model.addAttribute("department", employeeService.getDepartmentIdOfEmployee(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newEmployee(Model model, @ModelAttribute("employee") Employee employee) {
        model.addAttribute("departments", departmentService.findAll());
        return "employees/new";
    }

    @PostMapping()
    public String createEmployee(Model model, @ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "employees/new";
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String editEmployee(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.findOne(id));
        model.addAttribute("departments", departmentService.findAll());
        return "employees/edit";
    }

    @PatchMapping("/{id}")
    public String updateEmployee(Model model, @ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "employees/edit";
        }
        employeeService.update(id, employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String deleteEmploye(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
