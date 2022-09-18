package ru.vasiljeva.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Setter
@Getter
@NoArgsConstructor
@SQLDelete(sql = "update product set deleted=true where id=?")
@Where(clause = "deleted = false")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 30, nullable = false)
	private String name;

	@Column(name = "price", precision = 10, scale = 2, nullable = true)
	private BigDecimal cost;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;
	
	private String description;
}
