package ru.vasiljeva.service.impl;

import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.ItemDto;
import ru.vasiljeva.exceptions.ExceptionType;
import ru.vasiljeva.exceptions.ServiceException;
import ru.vasiljeva.model.Cart;
import ru.vasiljeva.model.Item;
import ru.vasiljeva.model.Product;
import ru.vasiljeva.model.QCart;
import ru.vasiljeva.model.QProduct;
import ru.vasiljeva.repository.CartRepository;
import ru.vasiljeva.repository.ItemRepository;
import ru.vasiljeva.repository.ProductRepository;
import ru.vasiljeva.service.CartService;
import ru.vasiljeva.utils.MappingUtils;

@Service
@RequiredArgsConstructor
@Setter
public class CartServiceImpl implements CartService {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MappingUtils mappingUtils;

	@Override
	public CartDto getCustomerCart(Long customerId) {
		//@formatter:off
		return this.cartRepository
				.findOne(QCart.cart.customer.id.eq(customerId))
				.map(mappingUtils::mapToDto)
				.orElse(new CartDto(customerId));
		//@formatter:on
	}

	@Override
	public CartDto addProductToCart(Long customerId, ItemDto item) {
		Cart cart = this.cartRepository.findOne(QCart.cart.customer.id.eq(customerId)).orElse(new Cart());
		Set<Item> items = cart.getItems();
		Long productId = item.getProductId();
		Long quantity = item.getQuantity();

		Optional<Item> existingItem = items.stream().filter(i -> i.getProduct().getId() == productId).findFirst();
		if (existingItem.isPresent()) {
			existingItem.get().setQuantity(quantity + existingItem.get().getQuantity());
		} else {
			Item newItem = new Item();
			Product product = this.productRepository.findOne(QProduct.product.id.eq(productId))
					.orElseThrow(() -> new ServiceException(ExceptionType.NOT_FOUND, "product", "id=" + productId, ""));
			newItem.setProduct(product);
			newItem.setCart(cart);
			newItem.setQuantity(quantity);
			items.add(newItem);
		}
		return mappingUtils.mapToDto(this.cartRepository.saveAndFlush(cart));
	}

	@Override
	public void removeItemFromCart(Long customerId, Long itemId) {
		this.itemRepository.deleteById(itemId);

	}

	@Override
	public void updateItemFromCart(Long customerId, ItemDto item) {
		this.itemRepository.saveAndFlush(mappingUtils.mapToEntity(item));
	}

}
