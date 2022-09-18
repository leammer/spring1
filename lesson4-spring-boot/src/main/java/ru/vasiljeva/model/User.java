package ru.vasiljeva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "FK_username", columnNames = "username") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false, length = 1024)
	private String password;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Customer customer;

	@ManyToMany(mappedBy = "users")
	private List<Role> roles;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
