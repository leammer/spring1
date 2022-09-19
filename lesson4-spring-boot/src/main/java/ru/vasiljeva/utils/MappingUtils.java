package ru.vasiljeva.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import ru.vasiljeva.dto.PersonalInfoDto;
import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.ItemDto;
import ru.vasiljeva.dto.ProductDto;
import ru.vasiljeva.dto.UserDto;
import ru.vasiljeva.model.Cart;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.model.Role;
import ru.vasiljeva.model.User;

@Mapper(componentModel = "spring")
public interface MappingUtils {
	@Mapping(target = "title", source = "entity.name")
	@Mapping(target = "price", source = "entity.cost")
	ProductDto mapToDto(Product entity);

	@Mapping(target = "name", source = "dto.title")
	@Mapping(target = "cost", source = "dto.price")
	Product mapToEntity(ProductDto dto);

	@Mapping(target = "productId", source = "entity.product.id")
	@Mapping(target = "productTitle", source = "entity.product.name")
	@Mapping(target = "productPrice", source = "entity.product.cost")
	ItemDto mapToDto(Item entity);

	@Mapping(target = "product.id", source = "productId")
	Item mapToEntity(ItemDto dto);

	PersonalInfoDto mapToDto(Customer entity);

	Customer mapToEntity(PersonalInfoDto dto);

	@Mapping(target = "cart", source = "entity.items")
	CartDto mapToDto(Cart entity);

	@Mapping(target = "items", source = "dto.cart")
	Cart mapToEntity(CartDto dto);

	@Mapping(target = "roles", qualifiedByName = "RoleToString")
	UserDto mapToDto(User entity);

	@Named("RoleToString")
	default String roleToString(Role role) {
		return role.getName();
	}
}
