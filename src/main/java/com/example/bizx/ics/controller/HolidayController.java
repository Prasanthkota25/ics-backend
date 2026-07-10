package com.example.bizx.ics.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bizx.ics.LeaveEntity.Holiday;
import com.example.bizx.ics.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

	private final HolidayService service;

	public HolidayController(HolidayService service) {
		this.service = service;
	}

	// GET /api/holidays?year=2026
	@GetMapping
	public ResponseEntity<List<Holiday>> getByYear(@RequestParam(defaultValue = "2026") int year) {
		return ResponseEntity.ok(service.getByYear(year));
	}

	// GET /api/holidays/dates?year=2026 → ["2026-01-01", ...]
	@GetMapping("/dates")
	public ResponseEntity<List<String>> getDates(@RequestParam(defaultValue = "2026") int year) {
		return ResponseEntity.ok(service.getHolidayDates(year));
	}

	@PostMapping
	public ResponseEntity<Holiday> add(@RequestBody Holiday holiday) {
		return ResponseEntity.ok(service.add(holiday));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}