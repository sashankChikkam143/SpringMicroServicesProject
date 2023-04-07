package com.infy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import org.springframework.web.client.RestTemplate;

import com.infy.DTO.BookingDTO;
import com.infy.DTO.ErrorMessage;
import com.infy.DTO.LoginDTO;
import com.infy.DTO.UserDTO;
import com.infy.exception.WecareException;
import com.infy.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*", allowedHeaders="*")
public class UserRestController {
	private static Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private UserDTO userDTO;
	
	
	@PostMapping
	public ResponseEntity<String> createUser(UserDTO userDTO,Errors error)
	{
		logger.info("In CreateUser Method");
		String response="";
		if(error.hasErrors())
		{
			response=error.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
			ErrorMessage er = new ErrorMessage();
			er.setMessage(response);
			return ResponseEntity.ok(er.getMessage());
		}
		else
		{
			response=userService.createCoach(userDTO);
			return ResponseEntity.ok(response);
		}
	}
	@CircuitBreaker(name="FailureService",fallbackMethod="failureToGetUserLogin")
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) throws WecareException
	{
		logger.info("In LoggerUser Method");
		boolean response = false;
		response= userService.loginUser(loginDTO);
		return ResponseEntity.ok(response);
	}
	@CircuitBreaker(name="FailureService",fallbackMethod="failureToGetUserprofile")
	@GetMapping("/{UserId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable("UserId") String UserId)
	{
		logger.info("Getting User");
		UserDTO userDTO =new UserDTO();
		userDTO = userService.getUserprofile(UserId);
		return ResponseEntity.ok(userDTO);
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/booking/{userId}")
	@CircuitBreaker(name="FailureService",fallbackMethod="failureToShowAppointments")
	public List<BookingDTO> showMyAppointments(@PathVariable("userId") String userId)
	{
		logger.info("in show appointments");
		List<BookingDTO> bookingDTOList = new ArrayList<>();
		String UserUri=null;
		List<ServiceInstance> listofInstances = client.getInstances("BookService");
		if(listofInstances!=null && !listofInstances.isEmpty() )
		{
			UserUri=listofInstances.get(0).getUri().toString();
		}
		bookingDTOList = template.getForObject(UserUri+"/booking"+"{userId}",List.class);
		return bookingDTOList;
	}
	
	public List<BookingDTO> failureToShowAppointments(String userId,Throwable throwable)
	{
		logger.info("-----------in failure of ShowAppointments");
		return new ArrayList<>();
	}
	
	public UserDTO failureToGetUserprofile(String userId,Throwable throwable)
	{
		logger.info("-----------in failure of ShowAppointments");
		userDTO.setUserid(userId);
		return userDTO;
	}
	public boolean failureToGetUserLogin(LoginDTO loginDTO,Throwable throwable)
	{
		logger.info("-----------in failure of ShowAppointments");
		return false;
	}

}
