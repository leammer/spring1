package ru.vasiljeva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.vasiljeva.data.Product;
import ru.vasiljeva.data.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("products", repo.getAll());
		return "product";
	}

	@GetMapping("/{id}")
	public String getProductById(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", repo.findProductById(id));
        return "product_form";
	}
	
	@PostMapping
	public String saveProduct(@Validated Product product) {
		return "redirect:/product";
	}
	/*
	 * @RequestMapping(value = "/user", method = RequestMethod.POST) public String
	 * user(@Validated User user, Model model) {
	 * System.out.println("User Page Requested"); model.addAttribute("userName",
	 * user.getUserName()); return "user"; }
	 */
}
