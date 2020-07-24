package com.example.spring.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import lombok.*;

@Data
@Entity
@Table(name = "genders",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "code"),
				@UniqueConstraint(columnNames = "name"),
		})
public class Gender implements CrudEntity {

	@Id
	@Column
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;

	@Column
	String code;

	@Column
	String name;

}
