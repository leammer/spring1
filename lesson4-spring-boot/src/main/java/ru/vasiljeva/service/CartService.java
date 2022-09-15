package ru.vasiljeva.service;

import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.ItemDto;

public interface CartService {
	CartDto getCustomerCart(Long customerId);

	CartDto addProductToCart(Long customerId, ItemDto dto);

	void removeItemFromCart(Long customerId, Long itemId);

	void updateItemFromCart(Long customerId, ItemDto item);
}
