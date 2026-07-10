package com.example.bizx.ics.LeaveRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.LeaveEntity.LeaveRequest;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findByUserId(Integer userId);

	List<LeaveRequest> findByManagerId(Integer managerId);
}
