package com.infy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infy.DTO.BookingDTO;
import com.infy.entity.BookingEntity;
import com.infy.entity.UserEntity;
import com.infy.exception.ExceptionConstants;
import com.infy.exception.WecareException;
import com.infy.repositry.BookRepository;
import com.infy.utility.MailUtility;
@Service
public class BookingService {
	private static Logger logger = LoggerFactory.getLogger(BookingService.class);
	@Autowired
	private BookRepository bookRepository;

	
	private Environment environment;
	
	@Autowired
	MailUtility mail;
	
	public boolean bookApointment(String userId, String coachId, LocalDate appointmentDate, String slot) throws WecareException
	{
		logger.info("In Booking Appointment");
		List<BookingEntity> bookings = new ArrayList<>();
		bookings = bookRepository.findBookingByUserId(userId,LocalDate.now());
		System.out.print(bookings);
		boolean response=false;
		if(bookings.isEmpty())
		{
			BookingEntity book = new BookingEntity();
			book.setCoachId(coachId);
			book.setUserId(userId);
			book.setAppointmentDate(appointmentDate);
			book.setSlot(slot);
			bookRepository.save(book);
			System.out.print(book);
			response = true;
			return response;
			
		}
		int count=0;
		for(BookingEntity bookingEntity:bookings)
		{
			if ((appointmentDate.equals(bookingEntity.getAppointmentDate())) && (slot.equals(bookingEntity.getSlot())))
			{
				count++;
			}
		}
		if(count==0)
		{
			BookingEntity book = new BookingEntity();
			book.setCoachId(coachId);
			book.setUserId(userId);
			book.setAppointmentDate(appointmentDate);
			book.setSlot(slot);
			bookRepository.save(book);
			System.out.print(book);
			response= true;
			
			/*Optional<UserEntity> user;
			user = userRepository.findByUserId(userId);
			
			Optional<CoachEntity> coach;
			coach = coachRepository.findByCoachId(coachId);
			new MailUtility().sendSchedulingEmail(user.get().getName(), coach.get().getName(),user.get().getEmail(),
					book.getBookingId(),book.getSlot(),book.getAppointmentDate());*/
			return response;
					
		}
		else
		{
			throw new WecareException(environment.getProperty(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString()));
		}
	
	}
	@SuppressWarnings("deprecation")
	public boolean rescheduleAppointMent(Integer bookingId, String slot,LocalDate appointmentDate)
	{
		logger.info("In reschedule Appointment");
		BookingEntity booki;
		List<BookingEntity> bookings = new ArrayList<>();
		booki = bookRepository.getOne(bookingId);
		bookings = bookRepository.findBookingByUserId(booki.getUserId(),LocalDate.now());
		System.out.print(bookings);
		boolean response=false;
		if(bookings.isEmpty())
		{
			BookingEntity book = new BookingEntity();
			book.setUserId(booki.getUserId());
			book.setAppointmentDate(appointmentDate);
			book.setSlot(slot);
			bookRepository.save(book);
			System.out.print(book);
			response=true;
			return response;
			
		}
		for(BookingEntity bookingEntity:bookings)
		{
			if ((appointmentDate.equals(bookingEntity.getAppointmentDate())) && (booki.getUserId().equals(bookingEntity.getUserId()))
					&&(slot.equals(bookingEntity.getSlot())))
			{
				BookingEntity book = new BookingEntity();
				book.setAppointmentDate(appointmentDate);
				book.setSlot(slot);
				response=true;
				
				
			}
			
		}
		return response;

		
	}
	public void cancelAppointment(Integer bookingId) {
		logger.info("Cancel Method");
		Optional<BookingEntity> book;
		book=bookRepository.findById(bookingId);
		if(book.isPresent())
		{
			bookRepository.deleteById(bookingId);
			@SuppressWarnings("unused")
			Optional<UserEntity> user;
			/*user = userRepository.findByUserId(book.get().getUserId());
			@SuppressWarnings("unused")
			Optional<CoachEntity> coach;
			coach = coachRepository.findByCoachId(book.get().getCoachId());
			new MailUtility().sendCancellingEmail(user.get().getName(), user.get().getName(),
					user.get().getEmail(), book.get().getBookingId(), book.get().getSlot(), book.get().getAppointmentDate());*/
			
			
		}
		
	}
	@SuppressWarnings("deprecation")
	public BookingDTO BookingId(Integer BookingId)
	{
		logger.info("in BookingId");
		BookingEntity bookingEntity;
		bookingEntity = bookRepository.getOne(BookingId);
		return BookingEntity.prepareBookingDTO(bookingEntity);
		
		
	}
	public List<BookingDTO>findbookingByUserId(String UserId)
	{
		logger.info("In booking id");
		List<BookingEntity> bookings = new ArrayList<>();
		List<BookingDTO> bookingDTOList = new ArrayList<>();
		bookings = bookRepository.findBookingByUserId(UserId, LocalDate.now());
		for(BookingEntity bookingEntity:bookings)
		{
			bookingDTOList.add(BookingEntity.prepareBookingDTO(bookingEntity));
		}
		return bookingDTOList;
	}

	public List<BookingDTO> findBookingCoachId(String coachId) {
		logger.info("In booking id");
		List<BookingEntity> bookings = new ArrayList<>();
		List<BookingDTO> bookingDTOList = new ArrayList<>();
		bookings = bookRepository.findBookingByCoachId(coachId, LocalDate.now());
		for(BookingEntity bookingEntity:bookings)
		{
			bookingDTOList.add(BookingEntity.prepareBookingDTO(bookingEntity));
		}
		
		return bookingDTOList;
	
	}

}
