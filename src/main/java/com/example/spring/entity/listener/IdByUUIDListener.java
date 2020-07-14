package com.example.spring.entity.listener;

import java.util.*;

import javax.annotation.*;
import javax.persistence.*;

import lombok.extern.slf4j.*;

@Slf4j
public class IdByUUIDListener {

	@PrePersist
	public void prePersist(IdByUUID entity) {
		log.debug("{}", entity);
		entity.setId(UUID.randomUUID().toString());
	}

	@PreUpdate
	public void preUpdate(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PreRemove
	public void preRemove(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PreDestroy
	public void preDestroy(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PostPersist
	public void postPersist(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PostUpdate
	public void postUpdate(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PostRemove
	public void postRemove(IdByUUID entity) {
		log.debug("{}", entity);
	}

	@PostLoad
	public void postLoad(IdByUUID entity) {
		log.debug("{}", entity);
	}
}
