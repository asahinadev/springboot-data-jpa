package com.example.spring.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring.db.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

	@Query("FROM User u WHERE u.email = :email AND u.deleted IS NULL")
	public Optional<User> findByEmail(String email);

	@Query("FROM User u WHERE u.username = :username AND u.deleted IS NULL")
	public Optional<User> findByUsername(String username);

}
