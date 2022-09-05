package ru.vasiljeva.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.vasiljeva.dao.CustomerDao;
import ru.vasiljeva.dao.ItemDao;
import ru.vasiljeva.dao.ProductDao;
import ru.vasiljeva.exceptions.EntityNotFoundException;
import ru.vasiljeva.model.Customer;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.service.CartService;

@Service
@NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id")
public class CartServiceImpl implements CartService {
	private Customer currentCustomer;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ProductDao productDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void setCurrentCustomer(Long id) {
		Customer customer = customerDao.getCustomerById(id);
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found");
		}
		this.currentCustomer = customer;
	}

	@Override
	public void addProductToCart(Long productId, Long quantity) {
		Product product = productDao.getProductById(productId);
		if (product == null) {
			throw new EntityNotFoundException("Product not found");
		}
		Set<Item> items = currentCustomer.getItems();
		Item item = items.stream().filter(it -> it.getProduct().getId().equals(productId)).findFirst().orElse(null);
		if (item != null) {
			item.setQuantity(item.getQuantity() + quantity);
			itemDao.updateItem(item);
		} else {
			Item newItem = new Item();
			newItem.setProduct(product);
			newItem.setCustomer(currentCustomer);
			newItem.setQuantity(quantity);
			items.add(newItem);
			customerDao.updateCustomer(currentCustomer);
		}
	}

	@Override
	public void removeProductFromCart(Long productId) {
		Product product = productDao.getProductById(productId);
		if (product == null) {
			throw new EntityNotFoundException("Product not found");
		}
		Set<Item> items = currentCustomer.getItems();
		Item item = items.stream().filter(it -> it.getProduct().getId().equals(productId)).findFirst().orElse(null);
		if (item != null) {
			Long newQuantity = item.getQuantity() - 1;
			if (newQuantity > 0) {
				item.setQuantity(newQuantity);
				itemDao.updateItem(item);
			} else {
				items.remove(item);
			}
		}
	}

	@Override
	public List<Item> getCartContent() {
		return new ArrayList<>(currentCustomer.getItems());
	}
}
