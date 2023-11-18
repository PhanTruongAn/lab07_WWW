package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
//@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProductListPaging(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Product> candidatePage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/product/listing";
    }

    @GetMapping("/products/show-add-form")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/product/add-form";
    }
    @GetMapping("/products/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){
      Optional<Product> op = productRepository.findById(id);
      if(op.isPresent()){
          model.addAttribute("update",op.get());
      }
      return "admin/product/update-form";
    }
    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute("update") Product product){
        productRepository.save(product);
        return "redirect:/products";
    }
    @PostMapping("/products/add")
    public String addCandidate(
            @ModelAttribute("product") Product product,
            BindingResult result, Model model) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            Product product = optional.get();
            product.setStatus(ProductStatus.TERMINATED);
            productRepository.save(product);
        }
        return "redirect:/products";
    }

}
