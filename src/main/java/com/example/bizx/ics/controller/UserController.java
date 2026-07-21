package com.example.bizx.ics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bizx.ics.UserEntity.LoginEntity;
import com.example.bizx.ics.dto.LoginRequestDTO;
import com.example.bizx.ics.dto.LoginResponseDTO;
import com.example.bizx.ics.dto.RegisterRequestDTO;
import com.example.bizx.ics.service.UserService;

@CrossOrigin(origins = { "http://localhost:3000",
		"https://prasanthkota25.github.io" }, allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
				RequestMethod.PUT, RequestMethod.OPTIONS })
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public List<LoginEntity> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/username/{username}")
	public LoginEntity getByUsername(@PathVariable String username) {
		return service.getByUsername(username);
	}

	@GetMapping("/id/{id}")
	public LoginEntity getById(@PathVariable Integer id) {
		return service.getById(id);
	}

//	@PostMapping("/register")
//	public LoginResponseDTO register(@RequestBody LoginEntity user) {
//		return service.register(user);
//	}

	@PostMapping("/register")
	public LoginResponseDTO register(@RequestBody RegisterRequestDTO dto) {

		LoginEntity user = new LoginEntity();

		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setGender(dto.getGender());

		return service.register(user);
	}

	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
		return service.login(dto);
	}

	@PutMapping("/resetpassword")
	public LoginResponseDTO resetPassword(@RequestBody LoginRequestDTO dto) {
		return service.resetPassword(dto);
	}

	@GetMapping("/search")
	public List<LoginEntity> searchUsers(@RequestParam String keyword) {
		return service.searchUsers(keyword);
	}
}