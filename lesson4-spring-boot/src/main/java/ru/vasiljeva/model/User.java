package ru.vasiljeva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	//@formatter:off
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = { 
					CascadeType.MERGE, 
					CascadeType.PERSIST, 
					CascadeType.REFRESH
					})
	@JoinTable(
			name = "users_roles", 
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = {	@JoinColumn(name = "role_id") }, 
			foreignKey = @ForeignKey(name = "FK_roles_users"), 
			inverseForeignKey = @ForeignKey(name = "FK_users_roles") 
			)
	//@formatter:on
	private Set<Role> roles = new HashSet<>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
