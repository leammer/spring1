package ru.vasiljeva.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_cart_customer"))
	private Customer customer;

	@OneToMany(mappedBy = "cart", cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.MERGE }, orphanRemoval = true)
	private Set<Item> items;
}
