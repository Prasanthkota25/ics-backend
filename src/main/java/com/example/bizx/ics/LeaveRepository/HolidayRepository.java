package com.example.bizx.ics.LeaveRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.LeaveEntity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	List<Holiday> findByYear(int year);
}