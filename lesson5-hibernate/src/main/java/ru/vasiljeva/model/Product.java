package ru.vasiljeva.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Entity
@Table(name = "Product")
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 30)
	private @NonNull String name;

	@Column(name = "price")
	private Double cost;

	public Product(@NonNull String name, Double cost) {
		super();
		this.name = name;
		this.cost = cost;
	}
}
