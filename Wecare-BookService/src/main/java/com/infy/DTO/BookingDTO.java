package com.infy.DTO;

import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
	private Integer bookingId;
	private String userId;
	private String CoachId;
	private LocalDate appointmentDate;
	private String slot;
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
	public String getCoachId() {
		return CoachId;
	}
	public void setCoachId(String coachId) {
		CoachId = coachId;
	}
	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", CoachId=" + CoachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}
	
	
	

}
