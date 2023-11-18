package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.backend.services.CustomerServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServices customerServices;

    @GetMapping("/customers")
    private String showCustomerList(
            Model model,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Customer> candidatePage = customerServices
                .findPaginated(currentPage - 1,pageSize,"name","asc");

        model.addAttribute("customerPage",candidatePage);
        int totalPage = candidatePage.getTotalPages();
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "admin/customer/listing";
    }

    @GetMapping("/customers/add-form")
    public String showAddForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "admin/customer/add-form";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer){
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }
    @GetMapping("/customers/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Optional<Customer> op = customerServices.findbyId(id);
        if(op.isPresent()){
            model.addAttribute("update",op.get());
        }
        return "admin/customer/update-form";
    }
    @PostMapping("/customers/update")
    public String updateCustomer(@ModelAttribute("update") Customer customer){
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }
}
