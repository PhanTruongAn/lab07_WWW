package vn.edu.iuh.fit.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.backend.services.EmployeeServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/employees")
    public String showEmployeeList(
            Model model,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Employee> candidatePage = employeeServices
                .findPaginated(currentPage - 1, pageSize, "name", "asc");
        model.addAttribute("employeePage", candidatePage);
        int totalPage = candidatePage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/emps/listing";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        Optional<Employee> op = employeeServices.findbyId(id);
        if (op.isPresent()){
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.TERMINATED);
            employeeRepository.save(employee);
        }
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/add-form")
    public String showAddForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "admin/emps/add-form";
    }
    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model){
        Optional<Employee> op = employeeServices.findbyId(id);
        LocalDate ob = op.get().getDob();
        if (op.isPresent()){
            model.addAttribute("date",ob);
            model.addAttribute("update",op.get());
        }
        return "admin/emps/update-form";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute("update") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/admin/employees";
    }


}
