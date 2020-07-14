package com.example.spring.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.example.spring.entity.*;

@Repository
public interface GenderRepository
	extends JpaRepository<Gender, String> {

	public Optional<Gender> findByName(String name);

	public Optional<Gender> findByCode(String code);

}
