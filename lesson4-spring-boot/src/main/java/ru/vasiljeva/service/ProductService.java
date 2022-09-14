package ru.vasiljeva.service;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

import ru.vasiljeva.dto.ProductDto;

public interface ProductService {
	ProductDto addProduct(ProductDto product);

	void removeProduct(Long id);

	ProductDto getProductById(Long id);

	Page<ProductDto> getAll(MultiValueMap<String, String> params);
}
