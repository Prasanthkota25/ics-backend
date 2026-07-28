package com.example.bizx.ics.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public double getPrivilegeLeaveBalance() {
		return privilegeLeaveBalance;
	}

	public void setPrivilegeLeaveBalance(double privilegeLeaveBalance) {
		this.privilegeLeaveBalance = privilegeLeaveBalance;
	}

	public double getCasualLeaveBalance() {
		return casualLeaveBalance;
	}

	public void setCasualLeaveBalance(double casualLeaveBalance) {
		this.casualLeaveBalance = casualLeaveBalance;
	}

	public double getAdoptionLeaveBalance() {
		return adoptionLeaveBalance;
	}

	public void setAdoptionLeaveBalance(double adoptionLeaveBalance) {
		this.adoptionLeaveBalance = adoptionLeaveBalance;
	}

	public double getBereavementLeaveBalance() {
		return bereavementLeaveBalance;
	}

	public void setBereavementLeaveBalance(double bereavementLeaveBalance) {
		this.bereavementLeaveBalance = bereavementLeaveBalance;
	}

	public double getPaternityLeaveBalance() {
		return paternityLeaveBalance;
	}

	public void setPaternityLeaveBalance(double paternityLeaveBalance) {
		this.paternityLeaveBalance = paternityLeaveBalance;
	}

	private double privilegeLeaveBalance;
	private double casualLeaveBalance;
	private double adoptionLeaveBalance;
	private double bereavementLeaveBalance;
	private double paternityLeaveBalance;
	private String firstname;
	private String lastname;

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String phone;

	private String role;

	private String bu;

	@Column(name = "bu_head")
	private String buHead;

	private String cubical;

	private String designation;

	private String extension;

	private String location;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getBuHead() {
		return buHead;
	}

	public void setBuHead(String buHead) {
		this.buHead = buHead;
	}

	public String getCubical() {
		return cubical;
	}

	public void setCubical(String cubical) {
		this.cubical = cubical;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getSbuHead() {
		return sbuHead;
	}

	public void setSbuHead(String sbuHead) {
		this.sbuHead = sbuHead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	private String manager;

	private String sbu;

	@Column(name = "sbu_head")
	private String sbuHead;

	private String status;

	@Column(name = "manager_id")
	private Integer managerId;

	public LoginEntity() {

	}

	public LoginEntity(String firstname, String lastname, String username, String password, String phone,
			String email) {

		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String gender;
}
