package com.infy.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.DTO.LoginDTO;
import com.infy.DTO.UserDTO;
import com.infy.entity.UserEntity;
import com.infy.exception.ExceptionConstants;
import com.infy.exception.WecareException;
import com.infy.repositry.UserRepositry;
import org.springframework.core.env.Environment;


@Service
public class UserService {
	@Autowired
	private UserRepositry userRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Environment environment;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	public String createCoach(UserDTO userDTO)
	{
		logger.info("In Creating coach");
		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		userRepository.save(userEntity);
		return userDTO.getUserid();
	}
	public boolean loginUser(LoginDTO loginDTO) throws WecareException
	{
		logger.info("in loginuser method");
		boolean response = false;
		Optional<UserEntity> userEntity;
		userEntity = userRepository.findByUserId(loginDTO.getId());
		if(userEntity.get().getPassword().equals(loginDTO.getPassword()))
		{
			response = true;
		}
		else {
			throw new WecareException(environment.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}
		return response;
	}
	public UserDTO getUserprofile(String userId)
	{
		logger.info("in getuserProfile");
		Optional<UserEntity> userEntity;
		userEntity=userRepository.findByUserId(userId);
		UserDTO userDTO =mapper.map(userEntity, UserDTO.class);
		return userDTO;
		
	}
	
	
}
