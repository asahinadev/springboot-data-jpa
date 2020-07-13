package com.example.spring.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;

@Repository
public interface UserRepository
	extends JpaRepository<User, String> {

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String email);

}
