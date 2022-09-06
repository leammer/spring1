package ru.vasiljeva.service;

import java.util.List;
import org.springframework.util.MultiValueMap;

import ru.vasiljeva.dto.ProductDto;

public interface ProductService {
	void addProduct(ProductDto product);

	void removeProduct(Long id);

	ProductDto getProductById(Long id);

	List<ProductDto> getAll(MultiValueMap<String, String> params);
}
