package com.example.spring.service;

import java.util.*;

import org.springframework.data.jpa.repository.*;

public abstract class CrudService<T, ID, R extends JpaRepository<T, ID>> {

	protected R repository;

	protected CrudService(R repository) {
		this.repository = repository;
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	public List<T> insert(Iterable<T> entities) {
		return repository.saveAll(entities);
	}

	public long count() {
		return repository.count();
	}

	public T insert(T entity) {
		return repository.save(entity);
	}

	public List<T> update(Iterable<T> entities) {
		return repository.saveAll(entities);
	}

	public T update(T entity) {
		return repository.save(entity);
	}

	public void delete(T entity) {
		repository.delete(entity);
	}
}
