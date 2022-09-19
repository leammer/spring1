package ru.vasiljeva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	//@formatter:off
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "roles_users", 
			joinColumns = { @JoinColumn(name = "role_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "user_id") }, 
			foreignKey = @ForeignKey(name = "FK_users_roles"), 
			inverseForeignKey = @ForeignKey(name = "FK_roles_users")
			)
	//@formatter:on
	private List<User> users;

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
