package com.example.bizx.ics.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bizx.ics.LeaveEntity.LeaveType;
import com.example.bizx.ics.service.LeaveTypeService;

@RestController
@RequestMapping("/api/leave-types")
public class LeaveTypeController {

	private final LeaveTypeService service;

	public LeaveTypeController(LeaveTypeService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<LeaveType>> getAll() {
		return ResponseEntity.ok(service.getAllActive());
	}

	@PutMapping("/{id}")
	public ResponseEntity<LeaveType> update(@PathVariable Long id, @RequestParam int defaultDays) {
		return ResponseEntity.ok(service.updateDefaultDays(id, defaultDays));
	}
}