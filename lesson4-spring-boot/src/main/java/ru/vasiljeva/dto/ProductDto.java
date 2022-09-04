package ru.vasiljeva.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public class ProductDto {
	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	@Min(0)
	@NumberFormat(pattern = "#.00")
	private String price;

	public ProductDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
