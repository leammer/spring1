package ru.vasiljeva.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.exceptions.EntityNotFoundException;
import ru.vasiljeva.service.ProductService;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@GetMapping
	public String getAll(@RequestParam(required = false) MultiValueMap<String, String> params, Model model) {
		log.info("Get full product list");
		model.addAttribute("products", this.service.getAll(params));
		return "product";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable Long id, Model model) {
		log.info("Get product by id " + id);
		model.addAttribute("product", this.service.getProductById(id));
		return "product_form";
	}

	@GetMapping("/new")
	public String newProduct() {
		return "new_product_form";
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id, Model model) {
		log.info("Remove product by id " + id);
		this.service.removeProduct(id);
		return "redirect:/product";
	}

	@PostMapping
	public String saveProduct(@RequestBody @Valid ProductDto request, Model model) {
		log.info("Save product" + request);
		this.service.addProduct(request);
		return "redirect:/product";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
		model.addAttribute("message", e.getMessage());
		return "error_form";
	}
}
