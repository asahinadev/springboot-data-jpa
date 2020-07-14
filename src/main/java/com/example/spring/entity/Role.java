package com.example.spring.entity;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Transient;

import org.springframework.security.core.*;

import com.example.spring.entity.listener.*;

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
@EntityListeners({
		IdByUUIDListener.class
})
public class Role implements GrantedAuthority, IdByUUID {

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
