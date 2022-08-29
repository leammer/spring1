package ru.vasiljeva.data;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
	private Long id;
	private String name;
	private Float cost;

	public Product(String name, Float cost) {
		super();
		this.name = name;
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
