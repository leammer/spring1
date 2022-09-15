package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.BY_ID;
import static ru.vasiljeva.utils.AppConstants.REST_PRODUCT_CONTROLLER_MAPPING;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.service.ProductService;

@RestController
@RequestMapping(REST_PRODUCT_CONTROLLER_MAPPING)
public class ProductRestController {
	@Autowired
	private ProductService service;

	@GetMapping
	public Page<ProductDto> getAll(@RequestParam(required = false) MultiValueMap<String, String> params) {
		return this.service.getAll(params);
	}

	@GetMapping(BY_ID)
	public ProductDto getById(@PathVariable Long id) {
		return this.service.getProductById(id);
	}

	@PostMapping
	public ProductDto saveProduct(@RequestBody @Valid ProductDto request) {
		return this.service.addProduct(request);
	}

	@DeleteMapping(BY_ID)
	public void deleteById(@PathVariable Long id) {
		this.service.removeProduct(id);
	}
}
