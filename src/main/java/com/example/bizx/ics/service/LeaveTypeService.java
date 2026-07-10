package com.example.bizx.ics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bizx.ics.LeaveEntity.LeaveType;
import com.example.bizx.ics.LeaveRepository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	private final LeaveTypeRepository repo;

	public LeaveTypeService(LeaveTypeRepository repo) {
		this.repo = repo;
	}

	public List<LeaveType> getAllActive() {
		return repo.findByActiveTrue();
	}

	public LeaveType updateDefaultDays(Long id, int days) {
		LeaveType lt = repo.findById(id).orElseThrow(() -> new RuntimeException("Leave type not found: " + id));
		lt.setDefaultDays(days);
		return repo.save(lt);
	}
}