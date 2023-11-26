package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.backend.services.EmployeeServices;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/home")
    public String home() {
        return "admin/admin-page";
    }


    @GetMapping()
    private String getAccount(Model model){
        Employee employee = new Employee();
        model.addAttribute("getAccount",employee);
        return "admin/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        Employee employee = new Employee();
        model.addAttribute("getAccount",employee);
        return "admin/login";
    }

    @PostMapping("/login-action")
    public String loginAction(@ModelAttribute("getAccount") Employee employee, Model model){
        String url = "";
        Optional<Employee> op = employeeRepository.loginEmpl(employee.getEmail(),employee.getPhone());
        System.out.println(op);
        if(!op.isEmpty()){
            url = "redirect:/admin/home";
        }else {
            url = "redirect:/admin/login";
        }
        return url;
    }
}
