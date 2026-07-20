package com.example.bizx.ics.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bizx.ics.LeaveEntity.LeaveRequest;
import com.example.bizx.ics.LeaveRepository.LeaveRepository;
import com.example.bizx.ics.UserEntity.LoginEntity;
import com.example.bizx.ics.UserRepository.LoginRepository;
import com.example.bizx.ics.dto.LeaveRequestDTO;
import com.example.bizx.ics.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRepository leaveRepo;

	@Autowired
	private LoginRepository userRepo;

	// APPLY LEAVE
	@Override
	public String applyLeave(LeaveRequestDTO dto) {

		LoginEntity user = userRepo.findByUsername(dto.getUsername());

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		String gender = user.getGender().toUpperCase();

		if ("Maternity Leave".equals(dto.getLeaveType()) && !"FEMALE".equals(gender)) {
			throw new RuntimeException("Maternity Leave is only for Female employees");
		}

		if ("Paternity Leave".equals(dto.getLeaveType()) && !"MALE".equals(gender)) {
			throw new RuntimeException("Paternity Leave is only for Male employees");
		}

//		if ("Adoption Leave".equals(dto.getLeaveType())) {
//
//			Integer childAge = dto.getChildAgeInMonths();
//
//			if (childAge == null) {
//				throw new RuntimeException("Child age is mandatory");
//			}
//
//			if (dto.getAdoptionDate() == null) {
//				throw new RuntimeException("Adoption date is mandatory");
//			}
//
//			if (!dto.getFromDate().equals(dto.getAdoptionDate())) {
//				throw new RuntimeException("Adoption Leave must start from adoption date");
//			}
//
//			if (dto.getAdoptionDocument() == null || dto.getAdoptionDocument().trim().isEmpty()) {
//				throw new RuntimeException("Adoption document is mandatory");
//			}
//
//			double days = dto.getDays();
//
//			if ("MALE".equals(gender)) {
//
//				if (days > 5) {
//					throw new RuntimeException("Maximum 5 working days allowed");
//				}
//
//			} else if ("FEMALE".equals(gender)) {
//
//				if (childAge < 3) {
//
//					if (days > 84) {
//						throw new RuntimeException("Maximum 84 days allowed");
//					}
//
//				} else if (childAge <= 60) {
//
//					if (days > 42) {
//						throw new RuntimeException("Maximum 42 days allowed");
//					}
//
//				} else {
//
//					throw new RuntimeException("Adoption Leave allowed only up to 5 years");
//				}
//			}
//		}

		if ("Adoption Leave".equals(dto.getLeaveType())) {

			double days = dto.getDays();

			if ("MALE".equals(gender)) {

				if (days > 5) {
					throw new RuntimeException("Maximum 5 working days allowed");
				}

			} else if ("FEMALE".equals(gender)) {

				if (days > 84) {
					throw new RuntimeException("Maximum 84 days allowed");
				}

			}
		}

		double days = dto.getDays();

		LeaveRequest lr = new LeaveRequest();

		lr.setUserId(user.getId());
		lr.setManagerId(user.getManagerId());
		lr.setLeaveType(dto.getLeaveType());
		lr.setFromDate(dto.getFromDate());
		lr.setToDate(dto.getToDate());
		lr.setDays(days);
		lr.setDayType(dto.getDayType());
		lr.setReason(dto.getReason());
		lr.setAddress(dto.getAddress());

		lr.setAdoptionDate(dto.getAdoptionDate());
		lr.setChildAgeInMonths(dto.getChildAgeInMonths());
		lr.setAdoptionDocument(dto.getAdoptionDocument());

		lr.setStatus("PENDING");
		lr.setAppliedOn(LocalDateTime.now());

		lr.setUsername(user.getUsername());
		lr.setPhone(user.getPhone());

		System.out.println("Address Length : " + (lr.getAddress() == null ? 0 : lr.getAddress().length()));

		System.out.println("Reason Length : " + (lr.getReason() == null ? 0 : lr.getReason().length()));

		System.out.println("Leave Type Length : " + (lr.getLeaveType() == null ? 0 : lr.getLeaveType().length()));

		System.out.println("Username Length : " + (lr.getUsername() == null ? 0 : lr.getUsername().length()));

		System.out.println("Phone Length : " + (lr.getPhone() == null ? 0 : lr.getPhone().length()));
		leaveRepo.save(lr);

		return "Leave Applied Successfully";
	}

	// =========================
	// CALCULATE DAYS
	// =========================
	private double calculateDays(LeaveRequestDTO dto) {

		long diff = ChronoUnit.DAYS.between(dto.getFromDate(), dto.getToDate()) + 1;

		if (diff == 1) {
			if ("FULL".equals(dto.getDayType())) {
				return 1;
			} else {
				return 0.5;
			}
		}

		return diff;
	}

	// =========================
	// MY LEAVES (EMPLOYEE)
	// =========================
	@Override
	public List<LeaveRequest> getMyLeaves(String username) {

		LoginEntity user = userRepo.findByUsername(username);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		return leaveRepo.findByUserId(user.getId());
	}

	// =========================
	// APPROVE LEAVE
	// =========================
	@Override
	public String approveLeave(Long id, String remarks) {

		LeaveRequest leave = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));

		if ("APPROVED".equals(leave.getStatus())) {
			return "Already approved";
		}

		leave.setStatus("APPROVED");
		leave.setApproverRemarks(remarks);

		leaveRepo.save(leave);

		// 🔥 Deduct balance ONLY on approval
		if (!"LOP".equals(leave.getLeaveType())) {

			LoginEntity user = userRepo.findById(leave.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));

			double days = leave.getDays();

			switch (leave.getLeaveType()) {

			case "Privilege Leave":
				user.setPrivilegeLeaveBalance(user.getPrivilegeLeaveBalance() - days);
				break;

			case "Casual / Sick Leave":
				user.setCasualLeaveBalance(user.getCasualLeaveBalance() - days);
				break;

			case "Adoption Leave":
				user.setAdoptionLeaveBalance(user.getAdoptionLeaveBalance() - days);
				break;

			case "Bereavement Leave":
				user.setBereavementLeaveBalance(user.getBereavementLeaveBalance() - days);
				break;

			case "Paternity Leave":
				user.setPaternityLeaveBalance(user.getPaternityLeaveBalance() - days);
				break;
			}

			userRepo.save(user);
		}

		return "Leave Approved Successfully";
	}

	// =========================
	// REJECT LEAVE
	// =========================
	@Override
	public String rejectLeave(Long id, String remarks) {

		LeaveRequest leave = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));

		if ("APPROVED".equals(leave.getStatus())) {
			return "Cannot reject approved leave";
		}
		leave.setStatus("REJECTED");
		leave.setApproverRemarks(remarks);

		leaveRepo.save(leave);

		return "Leave Rejected Successfully";
	}

	@Override
	public String requestCancel(Long id) {

		LeaveRequest leave = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));

		// ✅ Allow both PENDING and APPROVED
		if (!"APPROVED".equals(leave.getStatus()) && !"PENDING".equals(leave.getStatus())) {
			throw new RuntimeException("Only pending or approved leave can be cancelled");
		}

		// ✅ Prevent duplicate cancel request
		if ("CANCEL_REQUESTED".equals(leave.getStatus())) {
			throw new RuntimeException("Cancellation already requested");
		}
		leave.setPreviousStatus(leave.getStatus());
		leave.setStatus("CANCEL_REQUESTED");
		leave.setApproverRemarks("Cancellation Requested by Employee");

		leaveRepo.save(leave);

		return "Cancellation Requested";
	}

//	@Override
//	public String handleCancelDecision(Long id, String action, String remarks) {
//
//		LeaveRequest leave = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
//
//		if (!"CANCEL_REQUESTED".equals(leave.getStatus())) {
//			throw new RuntimeException("Invalid cancel request");
//		}
//
//		LoginEntity user = userRepo.findById(leave.getUserId())
//				.orElseThrow(() -> new RuntimeException("User not found"));
//
//		if ("APPROVE".equalsIgnoreCase(action)) {
//
//			if ("APPROVED".equals(leave.getPreviousStatus())) {
//
//				if (!"LOP".equals(leave.getLeaveType())) {
//
//					double days = leave.getDays();
//
//					switch (leave.getLeaveType()) {
//
//					case "Privilege Leave":
//						user.setPrivilegeLeaveBalance(user.getPrivilegeLeaveBalance() + days);
//						break;
//
//					case "Casual / Sick Leave":
//						user.setCasualLeaveBalance(user.getCasualLeaveBalance() + days);
//						break;
//
//					case "Adoption Leave":
//						user.setAdoptionLeaveBalance(user.getAdoptionLeaveBalance() + days);
//						break;
//
//					case "Bereavement Leave":
//						user.setBereavementLeaveBalance(user.getBereavementLeaveBalance() + days);
//						break;
//
//					case "Paternity Leave":
//						user.setPaternityLeaveBalance(user.getPaternityLeaveBalance() + days);
//						break;
//					}
//
//					userRepo.save(user);
//				}
//			}
//
////			leave.setStatus("CANCELLED");
////			leave.setApproverRemarks("Cancellation Approved");
//
//			leave.setStatus(leave.getPreviousStatus());
//			leave.setApproverRemarks("Cancellation Rejected");
//		} else {
//
//			// ✅ restore previous state
//			leave.setStatus(leave.getPreviousStatus());
//			leave.setApproverRemarks("Cancellation Rejected");
//		}
//
//		leaveRepo.save(leave);
//
//		return "Cancellation processed";
//	}

	@Override
	public String handleCancelDecision(Long id, String action, String remarks) {

		LeaveRequest leave = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));

		if (!"CANCEL_REQUESTED".equals(leave.getStatus())) {
			throw new RuntimeException("Invalid cancel request");
		}

		LoginEntity user = userRepo.findById(leave.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if ("APPROVE".equalsIgnoreCase(action)) {

			// ✅ CREDIT BACK LEAVE if previously APPROVED
			if ("APPROVED".equals(leave.getPreviousStatus())) {

				if (!"LOP".equals(leave.getLeaveType())) {

					double days = leave.getDays();

					switch (leave.getLeaveType()) {

					case "Privilege Leave":
						user.setPrivilegeLeaveBalance(user.getPrivilegeLeaveBalance() + days);
						break;

					case "Casual / Sick Leave":
						user.setCasualLeaveBalance(user.getCasualLeaveBalance() + days);
						break;

					case "Adoption Leave":
						user.setAdoptionLeaveBalance(user.getAdoptionLeaveBalance() + days);
						break;

					case "Bereavement Leave":
						user.setBereavementLeaveBalance(user.getBereavementLeaveBalance() + days);
						break;

					case "Paternity Leave":
						user.setPaternityLeaveBalance(user.getPaternityLeaveBalance() + days);
						break;
					}

					userRepo.save(user);
				}
			}

			leave.setStatus("CANCELLED");

		} else {

			// ✅ revert to previous
			leave.setStatus(leave.getPreviousStatus());
		}

		// ✅ ✅ IMPORTANT FIX — use manager remarks
		leave.setApproverRemarks(remarks);

		leaveRepo.save(leave);

		return "Cancellation processed";
	}

	@Override
	public List<LeaveRequest> getTeamLeaves(Integer managerId) {

		List<LeaveRequest> leaves = leaveRepo.findByManagerId(managerId);

		for (LeaveRequest leave : leaves) {

			LoginEntity user = userRepo.findById(leave.getUserId()).orElse(null);

			if (user != null) {
				leave.setUsername(user.getUsername());
				leave.setPhone(user.getPhone());
			}
		}

		return leaves;
	}

}