package com.example.bizx.ics.LeaveEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_types")
public class LeaveType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true, length = 100)
	private String name;

	@Column(name = "default_days", nullable = false)
	private int defaultDays;

	@Column(name = "is_dynamic", nullable = false)
	private boolean dynamic = false;

	@Column(name = "is_active", nullable = false)
	private boolean active = true;

	// ---------- Getters ----------
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDefaultDays() {
		return defaultDays;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public boolean isActive() {
		return active;
	}

	// ---------- Setters ----------
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefaultDays(int d) {
		this.defaultDays = d;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}