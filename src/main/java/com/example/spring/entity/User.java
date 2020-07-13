package com.example.spring.entity;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.springframework.security.core.userdetails.*;

import lombok.*;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
		name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email"),
		})
public class User
	implements UserDetails {

	@Id
	@Column
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

	@ManyToMany
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	List<Role> roles;

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		if (accountExpired == null) {
			return true;
		}
		return accountExpired.isAfter(LocalDateTime.now());
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		if (credentialsExpired == null) {
			return true;
		}
		return credentialsExpired.isAfter(LocalDateTime.now());
	}

	@Transient
	@Override
	public List<Role> getAuthorities() {
		return roles;
	}
}
