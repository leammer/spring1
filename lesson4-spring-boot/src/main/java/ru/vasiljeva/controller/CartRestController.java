package ru.vasiljeva.controller;

import static ru.vasiljeva.utils.AppConstants.REST_CART_CONTROLLER_MAPPING;

import javax.validation.Valid;

import static ru.vasiljeva.utils.AppConstants.BY_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vasiljeva.dto.CartDto;
import ru.vasiljeva.dto.ItemDto;
import ru.vasiljeva.service.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(REST_CART_CONTROLLER_MAPPING)
public class CartRestController {
	@Autowired
	private CartService service;

	@GetMapping(BY_ID)
	@Secured("ROLE_USER")
	public CartDto getCustomerCart(@PathVariable Long id) {
		return this.service.getCustomerCart(id);
	}

	@PostMapping(BY_ID)
	@Secured("ROLE_USER")
	public CartDto addProductToCart(@PathVariable Long id, @RequestBody @Valid ItemDto item) {
		return this.service.addProductToCart(id, item);
	}

	@DeleteMapping(BY_ID)
	@Secured("ROLE_USER")
	void removeItemFromCart(@PathVariable Long id, Long itemId) {

	}

	@PutMapping(BY_ID)
	@Secured("ROLE_USER")
	void updateItemFromCart(@PathVariable Long id, ItemDto item) {

	}

}
