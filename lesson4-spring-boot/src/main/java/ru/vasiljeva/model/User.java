package ru.vasiljeva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<Contact> contacts;

	@Column(nullable = false, length = 1024)
	private String password;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Customer customer;

	@ManyToMany(mappedBy = "users")
	private List<Role> roles;

	@Embedded
	private Passport passport;

	public User(String username, List<Contact> contacts, String password) {
		this.username = username;
		this.contacts = contacts;
		this.password = password;
	}
}
