package ru.vasiljeva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import ru.vasiljeva.data.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String listPage(Model model) {
		model.addAttribute("products", productRepository.getAll());
		return "product";
	}

	@GetMapping("/{id}")
	public String form(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", productRepository.findProductById(id));
		return "product_form";
	}

	/*
	 * @PostMapping public String saveUser(Product product) {
	 * productRepository.update(product); return "redirect:/user"; }
	 */
}
