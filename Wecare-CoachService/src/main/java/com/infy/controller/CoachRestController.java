package com.infy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infy.DTO.BookingDTO;
import com.infy.DTO.CoachDTO;
import com.infy.DTO.ErrorMessage;
import com.infy.DTO.LoginDTO;
import com.infy.exception.WecareException;
import com.infy.service.CoachSerive;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/coaches")
@CrossOrigin(origins="*",allowedHeaders="*")
public class CoachRestController {
	private  static Logger logger = LoggerFactory.getLogger(CoachRestController.class);
	@Autowired
	private CoachSerive coachService;
	
	@Autowired
	private CoachFeign feignBook;
	
	@Autowired
	private CoachDTO coachDTO; 
	
	
	@PostMapping
	public ResponseEntity<String> createCoach(@RequestBody CoachDTO coachDTO, Errors error)
	{
		logger.info("Creating Coach Method");
		String response="";
		if(error.hasErrors())
		{
			response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
			ErrorMessage er = new ErrorMessage();
			er.setMessage(response);
			return ResponseEntity.ok(er.getMessage());
		}
		else
		{
			response= coachService.createCoach(coachDTO);
			return ResponseEntity.ok(response);
			
		}
	}
	@CircuitBreaker(name="FailureService", fallbackMethod="failureToGetCoachLogin")
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO loginDTO) throws WecareException
	{
		logger.info("Logging in");
		boolean response = false;
		response = coachService.loginCoach(loginDTO);
		System.out.print(response);
		return ResponseEntity.ok(response);
			
	}
	@GetMapping("/{coachId}")
	@CircuitBreaker(name="FailureService", fallbackMethod="failureToGetCoachprofile")
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable("coachId")String coachId)
	{
		logger.info("Entering Into Coach Profile");
		CoachDTO coach = new CoachDTO();
		coach = coachService.getCoachProfile(coachId);
		return ResponseEntity.ok(coach);
		
	}
	@GetMapping("/all")
	public List<CoachDTO> showAllCoaches()
	{
		logger.info("To Get all showAllCoaches");
		List<CoachDTO> arr = new ArrayList<>();
		arr=coachService.showAllCoaches();
		return arr;
	}
	@CircuitBreaker(name="FailureService", fallbackMethod="failureToShowSechdule")
	@GetMapping("/booking/{coachId}")
	public List<BookingDTO> showMySchedule(String coachId)
	{
		logger.info("in showmySchedule");
		List<BookingDTO> dtoBook = new ArrayList<>();
		dtoBook = feignBook.findBookingByCoachId(coachId);
		return dtoBook;
		
	}
	public List<BookingDTO> failureToShowSechdule(String coachId,Throwable throwable)
	{
		logger.info("-----------in failure of ShowSechudule");
		return new ArrayList<>();
	}
	
	public CoachDTO failureToGetCoachprofile(String coachId,Throwable throwable)
	{
		logger.info("-----------in failure of ShowAppointments");
		coachDTO.setCoachId(coachId);
		return coachDTO;
	}
	public boolean failureToGetCoachLogin(LoginDTO loginDTO,Throwable throwable)
	{
		logger.info("-----------in failure of ShowAppointments");
		return false;
	}

	
	
	
 
}
