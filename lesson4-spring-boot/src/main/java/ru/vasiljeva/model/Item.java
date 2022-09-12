package ru.vasiljeva.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Item")
@Setter
@Getter
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	private Product product;

	@JoinColumn(name = "cart_id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Cart cart;

	@Column(nullable = true)
	private Long quantity;
}
