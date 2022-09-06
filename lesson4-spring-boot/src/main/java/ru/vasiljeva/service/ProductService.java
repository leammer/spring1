package ru.vasiljeva.service;

import java.util.List;
import org.springframework.util.MultiValueMap;

import ru.vasiljeva.dto.ProductDto;

public interface ProductService {
	public void addProduct(ProductDto product);

	public void removeProduct(Long id);

	public ProductDto getProductById(Long id);

	public List<ProductDto> getAll(MultiValueMap<String, String> params);
}
