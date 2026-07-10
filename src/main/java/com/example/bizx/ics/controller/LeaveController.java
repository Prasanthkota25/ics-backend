package com.example.bizx.ics.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bizx.ics.LeaveEntity.LeaveRequest;
import com.example.bizx.ics.dto.LeaveRequestDTO;
import com.example.bizx.ics.service.LeaveService;

@RestController
@RequestMapping("/leave")
//@CrossOrigin(origins = "http://localhost:3000")
public class LeaveController {

	@Autowired
	private LeaveService service;

	@PostMapping("/apply")
	public String apply(@RequestBody LeaveRequestDTO dto) {
		return service.applyLeave(dto);
	}

	@GetMapping("/my/{username}")
	public List<LeaveRequest> myLeaves(@PathVariable String username) {
		return service.getMyLeaves(username);
	}

//	@GetMapping("/team/{managerId}")
//	public List<LeaveRequest> teamLeaves(@PathVariable Integer managerId) {
//		return service.getTeamLeaves(managerId);
//	}
	@GetMapping("/team")
	public List<LeaveRequest> teamLeaves(@RequestHeader("managerId") Integer managerId) {
		return service.getTeamLeaves(managerId);
	}

	@PutMapping("/approve/{id}")
	public String approve(@PathVariable Long id, @RequestBody Map<String, String> body) {
		return service.approveLeave(id, body.get("remarks"));
	}

	@PutMapping("/reject/{id}")
	public String reject(@PathVariable Long id, @RequestBody Map<String, String> body) {
		return service.rejectLeave(id, body.get("remarks"));
	}

	@PutMapping("/request-cancel/{id}")
	public String requestCancel(@PathVariable Long id) {
		return service.requestCancel(id);
	}

	@PutMapping("/cancel-decision/{id}/{action}")
	public ResponseEntity<?> handleCancelDecision(@PathVariable Long id, @PathVariable String action,
			@RequestBody Map<String, String> body) {

		String remarks = body.get("remarks");

		return ResponseEntity.ok(service.handleCancelDecision(id, action, remarks));
	}

}