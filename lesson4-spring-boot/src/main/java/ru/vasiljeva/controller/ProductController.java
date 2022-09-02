package ru.vasiljeva.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.exceptions.EntityNotFoundException;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.model.ProductRepository;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductRepository repo;

	@GetMapping
	public String getAll(Model model) {
		log.info("Get full product list");
		model.addAttribute("products", repo.getAll());
		return "product";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") long id, Model model) {
		log.info("Get product by id " + id);
		Product product = repo.getById(id);
		if (product == null) {
			throw new EntityNotFoundException("Couldn't find product by id " + id);
		}
		log.info("Product: " + product);
		model.addAttribute("product", product);
		return "product_form";
	}

	@GetMapping("/new")
	public String newProduct() {
		return "new_product_form";
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, Model model) {
		log.info("Remove product by id " + id);
		repo.remove(id);
		return "redirect:/product";
	}

	@PostMapping
	public String save(@Valid Product product) {
		log.info("Save product" + product);
		repo.save(product);
		return "redirect:/product";
	}
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error_form";
    }
}
