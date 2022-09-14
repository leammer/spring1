package ru.vasiljeva.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.model.Product;

@Mapper(componentModel = "spring")
public interface MappingUtils {
	@Mapping(target = "title", source = "entity.name")
	@Mapping(target = "price", source = "entity.cost")
	ProductDto mapToProductDto(Product entity);

	@Mapping(target = "name", source = "dto.title")
	@Mapping(target = "cost", source = "dto.price")
	Product mapToProductEntity(ProductDto dto);
}
