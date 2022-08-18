package ru.vasiljeva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import ru.vasiljeva.data.Product;
import ru.vasiljeva.data.ProductRepository;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	    private final ProductRepository productRepository;

	    @GetMapping
	    public String listPage(Model model) {
	        model.addAttribute("productss", productRepository.getAll());
	        return "user";
	    }

	    @GetMapping("/{id}")
	    public String form(@PathVariable("id") long id, Model model) {
	        model.addAttribute("user", productRepository.findProductById(id));
	        return "user_form";
	    }

	    /*@PostMapping
	    public String saveUser(Product product) {
	        productRepository.update(product);
	        return "redirect:/user";
	    }*/
}
