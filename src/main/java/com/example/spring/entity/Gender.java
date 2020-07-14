package com.example.spring.entity;

import javax.persistence.*;

import com.example.spring.entity.listener.*;

import lombok.*;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genders",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "code"),
				@UniqueConstraint(columnNames = "name"),
		})
@EntityListeners({
		IdByUUIDListener.class
})
public class Gender implements IdByUUID {

	@Id
	@Column(length = 255, nullable = false)
	String id;

	@Column(length = 255, nullable = false)
	String code;

	@Column(length = 255, nullable = false)
	String name;

}
