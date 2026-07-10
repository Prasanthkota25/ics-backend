package com.example.bizx.ics.dto;

import java.time.LocalDate;

public class LeaveRequestDTO {

	private String approverRemarks;


	@jakarta.persistence.Transient
	private String username;

	@jakarta.persistence.Transient
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String leaveType;

	private LocalDate fromDate;

	private LocalDate toDate;

	private String dayType;

	private String reason;
	private String address;

	// ================= GETTERS & SETTERS =================

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getApproverRemarks() {
		return approverRemarks;
	}

	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}

	public double getDays() {
		return days;
	}

	public void setDays(double days) {
		this.days = days;
	}

	private double days;

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

	private LocalDate adoptionDate;

	private Integer childAgeInMonths;

	private String adoptionDocument;

}