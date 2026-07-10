package com.example.bizx.ics.service;

import java.util.List;

import com.example.bizx.ics.LeaveEntity.LeaveRequest;
import com.example.bizx.ics.dto.LeaveRequestDTO;

public interface LeaveService {

	String applyLeave(LeaveRequestDTO dto);

	List<LeaveRequest> getMyLeaves(String username);

	List<LeaveRequest> getTeamLeaves(Integer managerId);

	String approveLeave(Long id, String remarks);

	String rejectLeave(Long id, String remarks);

	String requestCancel(Long id);

	String handleCancelDecision(Long id, String action, String remarks);
}
