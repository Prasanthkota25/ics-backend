package com.example.bizx.ics.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bizx.ics.LeaveEntity.Holiday;
import com.example.bizx.ics.LeaveRepository.HolidayRepository;

@Service
public class HolidayService {

	private final HolidayRepository repo;

	public HolidayService(HolidayRepository repo) {
		this.repo = repo;
	}

	public List<Holiday> getByYear(int year) {
		return repo.findByYear(year);
	}

	public List<String> getHolidayDates(int year) {
		return repo.findByYear(year).stream().map(h -> h.getHolidayDate().toString()).collect(Collectors.toList());
	}

	public Holiday add(Holiday holiday) {
		return repo.save(holiday);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
