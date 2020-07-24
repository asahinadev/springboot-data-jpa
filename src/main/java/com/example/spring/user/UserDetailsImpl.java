package com.example.spring.user;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import javax.persistence.Transient;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.util.*;

import com.example.spring.entity.User;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	final User user;

	public UserDetailsImpl(User user) {
		Assert.notNull(user, "user");
		this.user = user;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		if (user.getRoles().isEmpty()) {
			return Arrays.asList(new UserGrantedAuthority());
		}
		return user.getRoles().stream()
				.map(UserGrantedAuthority::new)
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return Objects.isNull(user.getAccountExpired())
				|| user.getAccountExpired().isAfter(LocalDateTime.now());
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !user.isLocked();
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return Objects.isNull(user.getCredentialsExpired())
				|| user.getCredentialsExpired().isAfter(LocalDateTime.now());
	}

}
