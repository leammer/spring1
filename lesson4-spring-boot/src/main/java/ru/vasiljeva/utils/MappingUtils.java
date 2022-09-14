package ru.vasiljeva.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.ItemDto;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.model.Cart;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;

@Mapper(componentModel = "spring")
public interface MappingUtils {
	@Mapping(target = "title", source = "entity.name")
	@Mapping(target = "price", source = "entity.cost")
	ProductDto mapToProductDto(Product entity);

	@Mapping(target = "name", source = "dto.title")
	@Mapping(target = "cost", source = "dto.price")
	Product mapToProductEntity(ProductDto dto);

	@Mapping(target = "productId", source = "entity.product.id")
	@Mapping(target = "productTitle", source = "entity.product.name")
	@Mapping(target = "productPrice", source = "entity.product.cost")
	ItemDto mapToItemDto(Item entity);

	PersonalInfoDto mapToCustomerDto(Customer entity);

	CartDto mapToCartDto(Cart entity);
}
