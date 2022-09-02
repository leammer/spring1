package ru.vasiljeva.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	private Long id;

	private @NotBlank String name;

	private @NonNull Float cost;

	public Product(@NotBlank String name, @NonNull Float cost) {
		super();
		this.name = name;
		this.cost = cost;
	}
}
