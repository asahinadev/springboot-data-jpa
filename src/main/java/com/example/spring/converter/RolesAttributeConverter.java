package com.example.spring.converter;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.example.spring.db.values.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class RolesAttributeConverter
		implements AttributeConverter<List<Role>, String> {

	final ObjectMapper m = new ObjectMapper();

	@Override
	@SneakyThrows
	public String convertToDatabaseColumn(List<Role> attribute) {
		return m.writeValueAsString(attribute);
	}

	@Override
	@SneakyThrows
	public List<Role> convertToEntityAttribute(String dbData) {
		return m.readerFor(new TypeReference<List<Role>>() {
		}).readValue(dbData);
	}

}
