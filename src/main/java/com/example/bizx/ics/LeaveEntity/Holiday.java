package com.example.bizx.ics.LeaveEntity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "holidays")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "holiday_date", nullable = false, unique = true)
	private LocalDate holidayDate;

	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@Column(name = "year", nullable = false)
	private int year;

	// ---------- Getters ----------
	public Long getId() {
		return id;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	// ---------- Setters ----------
	public void setId(Long id) {
		this.id = id;
	}

	public void setHolidayDate(LocalDate date) {
		this.holidayDate = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
