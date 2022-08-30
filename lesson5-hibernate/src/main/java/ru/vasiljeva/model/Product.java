package ru.vasiljeva.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "Product")
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 30, nullable = false)
	private String name;

	@Column(name = "price", precision = 10, scale = 2, nullable = true)
	private BigDecimal cost;

	public Product(@NonNull String name, BigDecimal cost) {
		super();
		this.name = name;
		this.cost = cost;
	}
}
