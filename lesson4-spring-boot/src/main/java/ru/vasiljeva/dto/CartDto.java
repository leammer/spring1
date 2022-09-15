package ru.vasiljeva.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {
	private Long id;
	private Set<ItemDto> cart;

	public CartDto(Long id) {
		this.id = id;
		this.cart = new HashSet<ItemDto>();
	}
}
