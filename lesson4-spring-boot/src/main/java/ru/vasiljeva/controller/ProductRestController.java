package ru.vasiljeva.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.vasiljeva.dto.ErrorDto;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.service.ProductService;

@RestController
@RequestMapping("/app/products")
public class ProductRestController {
	@Autowired
	private ProductService service;

	@GetMapping
	public List<ProductDto> getAll(@RequestParam(required = false) MultiValueMap<String, String> params) {
		return this.service.getAll(params);
	}

	@GetMapping("/{id}")
	public ProductDto getById(@PathVariable Long id) {
		return this.service.getProductById(id);
	}

	@PostMapping
	public void saveProduct(@RequestBody @Valid ProductDto request) {
		this.service.addProduct(request);
	}

	@GetMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		this.service.removeProduct(id);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto validationExceptionHandler(Exception ex) {
		return new ErrorDto("Fix request", ex.getMessage());
	}
}
