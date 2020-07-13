package com.example.spring.entity;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Transient;

import org.springframework.security.core.*;

import lombok.*;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "code"),
				@UniqueConstraint(columnNames = "name"),
		})
public class Role implements GrantedAuthority {

	@Id
	@Column(length = 255, nullable = false)
	String id;
	@Column(length = 255, nullable = false)
	String code;
	@Column(length = 255, nullable = false)
	String name;

	@ManyToMany(mappedBy = "roles")
	List<User> users;

	@Transient
	@Override
	public String getAuthority() {
		return name;
	}
}
