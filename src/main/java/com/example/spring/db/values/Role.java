package com.example.spring.db.values;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	GUEST, USER, ADMIN;

	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}

	@Override
	public String toString() {
		return name();
	}

}
