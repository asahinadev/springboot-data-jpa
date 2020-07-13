package com.example.spring.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;

@Repository
public interface RoleRepository
	extends JpaRepository<Role, String> {

	public Optional<Role> findByName(String name);

	public Optional<Role> findByCode(String code);

}
