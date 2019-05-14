package com.example.spring.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.lang.Nullable;

import com.example.spring.db.values.Actived;

public class RolePropertyEditorSupport extends PropertyEditorSupport {

	@Override
	public void setAsText(@Nullable String text) {
		if (text == null) {
			setValue(null);
		}
		try {
			setValue(Actived.valueOf(text));
		} catch (Exception e) {
			// nop
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return (value != null ? value.toString() : "");
	}
}
