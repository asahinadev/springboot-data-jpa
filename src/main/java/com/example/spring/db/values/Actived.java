package com.example.spring.db.values;

import java.util.Objects;

import javax.persistence.AttributeConverter;

public enum Actived {
	DISABLED,
	ACITEVED;

	public static class Converter implements AttributeConverter<Actived, String> {

		@Override
		public String convertToDatabaseColumn(Actived value) {
			return value.ordinal() + "";
		}

		@Override
		public Actived convertToEntityAttribute(String ordinal) {
			for (Actived value : values()) {
				if (Objects.equals(value.ordinal() + "", ordinal)) {
					return value;
				}
			}
			return DISABLED;
		}
	}
}
