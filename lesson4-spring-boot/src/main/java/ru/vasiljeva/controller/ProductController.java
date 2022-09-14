package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.BY_ID;
import static ru.vasiljeva.utils.AppConstants.MODEL_ATTRIBUTE_ALL_PRODUCTS;
import static ru.vasiljeva.utils.AppConstants.MODEL_ATTRIBUTE_PRODUCT;
import static ru.vasiljeva.utils.AppConstants.MVC_CONTROLLER_MAPPING;
import static ru.vasiljeva.utils.AppConstants.NEW;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.service.ProductService;

@Controller
@RequestMapping(MVC_CONTROLLER_MAPPING)
@Slf4j
public class ProductController {
	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@GetMapping
	public String getAll(@RequestParam(required = false) MultiValueMap<String, String> params, Model model) {
		log.info("Get full product list");
		model.addAttribute(MODEL_ATTRIBUTE_ALL_PRODUCTS, this.service.getAll(params));
		return "product";
	}

	@GetMapping(BY_ID)
	public String getById(@PathVariable Long id, Model model) {
		log.info("Get product by id " + id);
		model.addAttribute(MODEL_ATTRIBUTE_PRODUCT, this.service.getProductById(id));
		return "product_form";
	}

	@GetMapping(NEW)
	public String newProduct() {
		return "new_product_form";
	}

	@DeleteMapping(BY_ID)
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
}
