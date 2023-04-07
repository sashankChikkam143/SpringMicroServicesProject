package com.infy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.infy.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
	@Id
	@GenericGenerator(name="User_id_generator", strategy="com.infy.utility.UserIdGenerator")
	@GeneratedValue(generator="Coach_id_generator")
	private String userId;
	private String password;
	private String name;
	private LocalDate dateOfBirth;
	private char gender;
	private Long mobileNumber;
	private String email;
	private Integer pincode;
	private String city;
	private String state;
	private String country;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static UserDTO prepareUserDTO(UserEntity userEntity)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(userEntity.getUserId());
		userDTO.setName(userEntity.getName());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setCity(userEntity.getCity());
		userDTO.setCountry(userEntity.getCountry());
		userDTO.setDateOfBirth(userEntity.getDateOfBirth());
		userDTO.setGender(userEntity.getGender());
		userDTO.setMobileNumber(userEntity.getMobileNumber());
		userDTO.setPincode(userEntity.getPincode());
		userDTO.setState(userEntity.getState());
		return userDTO;
	}
	
	
}
