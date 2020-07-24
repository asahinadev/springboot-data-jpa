package com.example.spring.entity;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import lombok.*;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email"),
})
@EntityListeners({
		AuditingEntityListener.class
})
public class User implements CrudEntity {

	@Id
	@Column
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;

	@Column
	String username;

	@Column
	String email;

	@Column
	String password;

	@Column
	boolean enabled;

	@Column
	boolean locked;

	@Column(name = "credentials_expired")
	LocalDateTime credentialsExpired;

	@Column(name = "account_expired")
	LocalDateTime accountExpired;

	@CreatedDate
	@Column(name = "created")
	LocalDateTime created;

	@LastModifiedDate
	@Column(name = "updated")
	LocalDateTime updated;

	@Column(name = "deleted")
	LocalDateTime deleted;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	List<Role> roles;

}
