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

	public long countByUsername(String username);

	public long countByEmail(String email);

	public long countByUsernameAndIdNot(String username, String id);

	public long countByEmailAndIdNot(String email, String id);

}
