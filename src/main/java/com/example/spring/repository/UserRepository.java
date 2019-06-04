package com.example.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entity.User;

@Repository
public interface UserRepository
		extends JpaRepository<User, String> {

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String email);

	public Optional<User> findById(String email);

	public List<User> findAll();

}
