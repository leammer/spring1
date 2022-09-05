package ru.vasiljeva.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Customer")
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@OneToMany(mappedBy = "customer")
	private Set<Item> items;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", cart={" + items + "}]";
	}

}
