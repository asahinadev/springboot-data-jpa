package com.example.spring.entity;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import lombok.*;

@Data
@Entity
@Table(name = "roles", uniqueConstraints = {
		@UniqueConstraint(columnNames = "code"),
		@UniqueConstraint(columnNames = "name"),
})
public class Role implements CrudEntity {

	@Id
	@Column
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;

	@Column
	String code;

	@Column
	String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	List<User> users;

}
