package com.infy.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class CoachDTO {
	private String coachId;
	@NotNull(message="name should not empty")
	@Min(5)
	@Max(5)
	private String password;
	@NotNull
	@Min(3)
	@Max(50)
	private String name;
	private LocalDate dateOfbirth;
	private char gender;
	@NotNull
	@Min(10)
	@Max(10)
	private long mobileNumber;
	@NotNull
	@Min(3)
	@Max(50)
	private String speciality;
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfbirth() {
		return dateOfbirth;
	}
	public void setDateOfbirth(LocalDate dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
		
	@Override
	public String toString() {
		return "CoachDTO [coachId=" + coachId + ", password=" + password + ", name=" + name + ", dateOfbirth="
				+ dateOfbirth + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", speciality=" + speciality
				+ "]";
	}
	
	
	
	

}
