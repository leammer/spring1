package ru.vasiljeva.service;

import java.util.List;

import ru.vasiljeva.dto.ProductDto;

public interface ProductService {
	public void addProduct(ProductDto product);

	public void removeProduct(Long id);

	public ProductDto getProductById(Long id);

	public List<ProductDto> getAll(double minPrice, double maxPrice);
}
