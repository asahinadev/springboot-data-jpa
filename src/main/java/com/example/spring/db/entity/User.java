package com.example.spring.db.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring.db.values.Actived;
import com.example.spring.db.values.Role;

import lombok.Data;

@Data
@Entity
@Table(
		name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = { "username", "deleted" }),
				@UniqueConstraint(columnNames = { "email", "deleted" })
		})
@SuppressWarnings("serial")
public class User implements UserDetails {

	@Id
	@Column(nullable = false)
	String id;

	@Column(nullable = true, length = 255)
	String username;

	@Column(nullable = true, length = 255)
	String email;

	@Column(nullable = true, length = 255)
	String password;

	@Column(nullable = true)
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime created;

	@Column(nullable = true)
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime updated;

	@Column(nullable = true)
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime deleted;

	@Column(nullable = true)
	@Convert(converter = Actived.Converter.class)
	Actived actived = Actived.ACITEVED;

	@Override
	@Transient
	public List<Role> getAuthorities() {
		return Arrays.asList(Role.ADMIN, Role.USER);
	}

	@Override
	public boolean isAccountNonExpired() {
		// アカウント有効期限内
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 否ロック状態
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// パスワード有効期限内
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 有効判定
		return actived == Actived.ACITEVED;
	}

}
