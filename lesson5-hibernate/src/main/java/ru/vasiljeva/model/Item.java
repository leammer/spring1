package ru.vasiljeva.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Item")
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Product product;

	@JoinColumn(name = "customer_id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Customer customer;

	private Long quantity;

	@Override
	public String toString() {
		return "Item [name="+ product.getName()+", cost="+product.getCost() + ", quantity=" + quantity + "]";
	}
	
	
}
