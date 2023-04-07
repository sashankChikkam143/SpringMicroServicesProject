package com.infy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.infy.DTO.CoachDTO;

@Entity
@Table(name = "CoachId")
public class CoachEntity {
	@Id
	@GenericGenerator(name="Coach_id_generator", strategy="com.infy.utility.CoachIdGenerator")
	@GeneratedValue(generator="Coach_id_generator")
	private String coachId;
	private String password;
	private String name;
	private LocalDate dateOfbirth;
	private char gender;
	private long mobileNumber;
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
	public CoachDTO prepareCoachDTO(CoachEntity coachEntity) {
		CoachDTO coachDTO = new CoachDTO();
		coachDTO.setCoachId(coachEntity.getCoachId());
		coachDTO.setDateOfbirth(coachEntity.getDateOfbirth());
		coachDTO.setGender(coachEntity.getGender());
		coachDTO.setMobileNumber(coachEntity.getMobileNumber());
		coachDTO.setName(coachEntity.getName());
		coachDTO.setPassword(coachEntity.getPassword());
		coachDTO.setSpeciality(coachEntity.getSpeciality());
		
		return coachDTO;
	}
	

}
