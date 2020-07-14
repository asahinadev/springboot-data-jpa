package com.example.spring.entity.listener;

import java.io.*;

import org.apache.commons.lang3.*;

public interface IdByUUID
	extends Serializable, Comparable<IdByUUID> {

	public void setId(String uuid);

	public String getId();

	default int compareTo(IdByUUID o) {
		if (o == null || o.getId() == null) {
			return -1;
		}
		return StringUtils.compare(this.getId(), o.getId());
	}

}
