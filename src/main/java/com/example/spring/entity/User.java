package com.example.spring.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
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

	@Transient
	List<Roles> authorities;

	public List<Roles> getAuthorities() {

		if (authorities == null || authorities.isEmpty()) {
			return Arrays.asList(Roles.GUEST);
		}

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {

		if (accountExpired == null) {
			return true;
		}
		return accountExpired.isAfter(LocalDateTime.now());
	}

	@Override
	public boolean isAccountNonLocked() {

		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		if (credentialsExpired == null) {
			return true;
		}
		return credentialsExpired.isAfter(LocalDateTime.now());
	}

}
