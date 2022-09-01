package ru.vasiljeva.service;

import java.util.List;

import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;

public interface CartService {
	public void setCurrentCustomer(Long id);

	public void addProductToCart(Long productId, Long quantity);

	public void removeProductFromCart(Long productId);

	public List<Item> getCartContent();
}
