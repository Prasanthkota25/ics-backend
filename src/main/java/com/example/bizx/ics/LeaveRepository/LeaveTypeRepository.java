package com.example.bizx.ics.LeaveRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.LeaveEntity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
	List<LeaveType> findByActiveTrue();
}
