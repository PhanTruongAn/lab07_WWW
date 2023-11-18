package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "admin/admin-page";
    }

    @GetMapping("/login")
    public String login(){
        return "client/login";
    }
}
