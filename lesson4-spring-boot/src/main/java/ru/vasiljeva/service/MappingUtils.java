package ru.vasiljeva.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.model.Product;

@Service
public class MappingUtils {
	public ProductDto mapToProductDto(Product entity) {
		ProductDto dto = new ProductDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getName());
		dto.setPrice(entity.getCost().doubleValue());
		return dto;
	}

	public Product mapToProductEntity(ProductDto dto) {
		Product entity = new Product();
		entity.setId(dto.getId());
		entity.setName(dto.getTitle());
		entity.setCost(new BigDecimal(dto.getPrice()));
		return entity;
	}
}
