package com.infy.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import com.infy.DTO.BookingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class BookingEntity {
	@Id
	@Column(name="bookingid")
	private Integer bookingId;
	@Column(name="userid")
	private String userId;
	@Column(name="coachid")
	private String coachId;
	private LocalDate appointmentDate;
	private String slot;
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	@Override
	public String toString() {
		return "BookingEntity [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}
	
	public static BookingDTO prepareBookingDTO(BookingEntity bookingEntity)
	{
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingId(bookingEntity.getBookingId());
		bookingDTO.setUserId(bookingEntity.getUserId());
		bookingDTO.setSlot(bookingEntity.getSlot());
		bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
		bookingDTO.setCoachId(bookingEntity.getCoachId());
		return bookingDTO;
	}
}
