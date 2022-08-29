package ru.vasiljeva.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.data.Product;
import ru.vasiljeva.data.ProductRepository;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductRepository repo;

	@GetMapping
	public String getAll(Model model) {
		log.info("get full product list ");
		model.addAttribute("products", repo.getAll());
		return "product";
	}

	@GetMapping("/{id}")
	public String getProductById(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		log.info("get product by id " + id);

		Product product = repo.getById(id);
		if (product == null) {
			model.addAttribute("message", "Product by such id couldn't be found");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "error_form";
		}
		model.addAttribute("product", product);
		return "product_form";
	}

	@GetMapping("/new")
	public String addNewProduct(Model model) {
		return "new_product_form";
	}

	@PostMapping
	public String saveOrUpdate(@Validated Product product) {
		log.info("Save or update product " + product);
		repo.update(product);
		return "redirect:/product";
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable("id") Long id) {
		log.info("Remove product by id " + id);
		repo.remove(id);
		return "redirect:/product";
	}
}
