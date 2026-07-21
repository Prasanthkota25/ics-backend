package com.example.bizx.ics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bizx.ics.UserEntity.LoginEntity;
import com.example.bizx.ics.UserRepository.LoginRepository;
import com.example.bizx.ics.dto.LoginRequestDTO;
import com.example.bizx.ics.dto.LoginResponseDTO;
import com.example.bizx.ics.exception.InvalidCredentialsException;
import com.example.bizx.ics.exception.UserNotFoundException;
import com.example.bizx.ics.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LoginRepository repo;

	@Override
	public List<LoginEntity> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public LoginEntity getByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public LoginEntity getById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	@Override
	public LoginResponseDTO register(LoginEntity user) {

		if (repo.findByUsername(user.getUsername()) != null) {
			throw new IllegalArgumentException("Username already exists");
		}

		if (user.getGender() == null || user.getGender().isEmpty()) {
			throw new IllegalArgumentException("Gender is required");
		}

		user.setGender(user.getGender());

		// Default values for newly registered users
		user.setRole("EMPLOYEE");
		user.setStatus("ACTIVE");

		repo.save(user);

		return new LoginResponseDTO("Registration Successful");
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO dto) {

		LoginEntity user = repo.findByUsername(dto.getUsername());

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		if (dto.getPassword() == null || !user.getPassword().equals(dto.getPassword())) {
			throw new InvalidCredentialsException("Invalid credentials");
		}

		return new LoginResponseDTO("Login Successful");
	}

	@Override
	public LoginResponseDTO resetPassword(LoginRequestDTO dto) {

		LoginEntity user = repo.findByUsername(dto.getUsername());

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		user.setPassword(dto.getPassword());
		repo.save(user);

		return new LoginResponseDTO("Password reset successful");
	}

	@Override
	public List<LoginEntity> searchUsers(String keyword) {

		if (keyword == null || keyword.length() < 3) {
			return List.of();
		}

//		return repo.findByFirstnameStartingWithIgnoreCaseOrLastnameStartingWithIgnoreCase(keyword, keyword);

		return repo.searchUsers(keyword);
	}
}