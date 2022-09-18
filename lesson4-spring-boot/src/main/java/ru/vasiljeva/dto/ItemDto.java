package ru.vasiljeva.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private Long productId;
	@JsonProperty("title")
	private String productTitle;
	@JsonProperty("price")
	private Double productPrice;

	@NotNull
	@Min(0)
	private Long quantity;
}
