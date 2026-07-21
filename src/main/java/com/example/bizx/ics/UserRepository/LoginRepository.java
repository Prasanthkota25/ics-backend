package com.example.bizx.ics.UserRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.UserEntity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

	LoginEntity findByUsername(String username);

	LoginEntity findByUsernameIgnoreCase(String username);

	List<LoginEntity> findByFirstnameStartingWithIgnoreCaseOrLastnameStartingWithIgnoreCase(String firstname,
			String lastname);
}
