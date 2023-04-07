package com.infy.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.DTO.BookingDTO;
import com.infy.exception.ExceptionConstants;
import com.infy.exception.WecareException;
import com.infy.service.BookingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.core.env.Environment;

@RestController
@RequestMapping
@CrossOrigin(origins="*", allowedHeaders="*")
public class BookRestController {
	private static Logger logger = LoggerFactory.getLogger(BookRestController.class);
	
	@Autowired
	private BookingService bookService;
	
	private Environment environment;
	
	
	@PostMapping("/users/{userId}/booking/{coachId}")
	public ResponseEntity<Boolean> bookApointment(@PathVariable("userId") String userId, @PathVariable("coachId") String coachId,String slot, LocalDate dateofAppointment)throws WecareException
	{
		logger.info("in book AppointMent Method");
		boolean response =false;
		response=bookService.bookApointment(userId, coachId, dateofAppointment, slot);
		if(response=false)
		{
			throw new WecareException();
		}
	return ResponseEntity.ok(response);

	}
	@PutMapping("/booking/{bookingId}")
	public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable("bookingId")Integer bookingId,String slot,LocalDate dateOfAppointMent) throws WecareException
	{
		logger.info("in reschedule AppointMent Method");
		boolean response =false;
		response=bookService.rescheduleAppointMent(bookingId, slot, dateOfAppointMent);
		if(response==false)
		{
			throw new WecareException(environment.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}
		return ResponseEntity.ok(response);
	}
	@DeleteMapping("/booking/{bookingId}")
	public void cancelappointment(@PathVariable("bookingId") Integer bookingId)
	{
		logger.info("In cancel AppointMent");
		bookService.cancelAppointment(bookingId);
	}
	
	@CircuitBreaker(name="FailureService" ,fallbackMethod="failureToFindBookCoachId")
	@GetMapping("/booking/{CoachId}")
	public List<BookingDTO> showMySchedule(@PathVariable("CoachId") String CoachId){
		return bookService.findBookingCoachId(CoachId);
		
	}
	@CircuitBreaker(name="FailureService" ,fallbackMethod="failureToFindBookUserId")
	@GetMapping("/booking/{userId}")
	public List<BookingDTO> showMyAppointments(@PathVariable("userId") String userId){
		return bookService.findBookingCoachId(userId);
		
	}
	
	public List<BookingDTO> failureToFindCoachId(String CoachId, Throwable throwable)
	{
		logger.info("-----------In fallback of finding CoachId");
		return new ArrayList<>();
	}
	public List<BookingDTO> failureToFindBookUserId(String userId, Throwable throwable)
	{
		logger.info("-----------In fallback of finding CoachId");
		return new ArrayList<>();
	}
	
	
}
