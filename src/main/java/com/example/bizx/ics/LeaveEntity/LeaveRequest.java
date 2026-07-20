package com.example.bizx.ics.LeaveEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class LeaveRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// Employee ID
	private Integer userId;

	// Manager ID
	private Integer managerId;

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	private String leaveType;

	private LocalDate fromDate;

	private LocalDate toDate;

	private double days;

	private String dayType;

	private String reason;

	private String status;
	private String previousStatus;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String address;
	private LocalDate adoptionDate;

	private Integer childAgeInMonths;

	private String adoptionDocument;

	public LocalDate getAdoptionDate() {
		return adoptionDate;
	}

	public void setAdoptionDate(LocalDate adoptionDate) {
		this.adoptionDate = adoptionDate;
	}

	public Integer getChildAgeInMonths() {
		return childAgeInMonths;
	}

	public void setChildAgeInMonths(Integer childAgeInMonths) {
		this.childAgeInMonths = childAgeInMonths;
	}

	public String getAdoptionDocument() {
		return adoptionDocument;
	}

	public void setAdoptionDocument(String adoptionDocument) {
		this.adoptionDocument = adoptionDocument;
	}

	// New Field
	private String approverRemarks;

	private LocalDateTime appliedOn;

	// ================= GETTERS & SETTERS =================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproverRemarks() {
		return approverRemarks;
	}

	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}

	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
	}

}
