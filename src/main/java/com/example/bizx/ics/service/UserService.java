package com.example.bizx.ics.service;

import java.util.List;

import com.example.bizx.ics.UserEntity.LoginEntity;
import com.example.bizx.ics.dto.LoginRequestDTO;
import com.example.bizx.ics.dto.LoginResponseDTO;

public interface UserService {
	List<LoginEntity> searchUsers(String keyword);

	List<LoginEntity> getAllUsers();

	LoginEntity getByUsername(String username);

	LoginEntity getById(Integer id);

	LoginResponseDTO register(LoginEntity user);

	LoginResponseDTO login(LoginRequestDTO dto);

	LoginResponseDTO resetPassword(LoginRequestDTO dto);
	
}