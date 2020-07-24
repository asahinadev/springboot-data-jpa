package com.example.spring.user;

import org.springframework.security.core.*;
import org.springframework.util.*;

import com.example.spring.entity.*;

@SuppressWarnings("serial")
public class UserGrantedAuthority implements GrantedAuthority {

	Role role;

	public UserGrantedAuthority() {

	}

	public UserGrantedAuthority(Role role) {
		Assert.notNull(role, "role");
		this.role = role;
	}

	@Override
	public String getAuthority() {
		if (role == null) {
			return "GUEST";
		}
		return role.getName();
	}

}
