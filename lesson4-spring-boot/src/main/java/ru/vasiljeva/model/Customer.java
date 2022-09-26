package ru.vasiljeva.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
@Table(name = "Customer")
@Setter
@Getter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@Embedded
	private Passport passport;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.MERGE })
	private Set<Contact> contacts = new HashSet<>();

	@OneToOne(orphanRemoval = false, cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_customer_user"))
	private User user;
}
