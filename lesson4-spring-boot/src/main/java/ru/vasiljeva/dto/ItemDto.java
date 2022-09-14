package ru.vasiljeva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	private Long id;

	@JsonProperty("product_id")
	private Long productId;
	@JsonProperty("title")
	private String productTitle;
	@JsonProperty("price")
	private Double productPrice;

	@JsonProperty("amount")
	private Long quantity;
}
