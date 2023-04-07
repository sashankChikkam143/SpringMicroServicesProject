package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.DTO.CoachDTO;
import com.infy.DTO.LoginDTO;
import com.infy.entity.CoachEntity;
import com.infy.exception.ExceptionConstants;
import com.infy.exception.WecareException;
import com.infy.repositry.CoachRepository;
import org.springframework.core.env.Environment;

@Service
public class CoachSerive {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CoachRepository coachrepo;
	
	private Environment environment;
	
	private static Logger logger = LoggerFactory.getLogger(CoachSerive.class);
	
	public String createCoach(CoachDTO coachDTO)
	{
		logger.info("In creating method");
		CoachEntity coach = mapper.map(coachDTO, CoachEntity.class);
		coachrepo.save(coach);
		return coachDTO.getCoachId();
	}
	public boolean loginCoach(LoginDTO loginDTO) throws WecareException
	{
		logger.info("In Login coach");
		boolean response=false;
		CoachEntity coachentity;
		System.out.print("hi");
		System.out.print("CoachId"+loginDTO.getId());
		coachentity=coachrepo.findByCoachId(loginDTO.getId()).get();
		if(coachentity.getPassword().equals(loginDTO.getPassword()))
		{
			response= true;
			System.out.print("Coach Entity"+ coachentity);
		}
		else {
			throw new WecareException(environment.getProperty(ExceptionConstants.COACH_NOT_FOUND.toString()));
		}
		
		return response;
	}

	public CoachDTO getCoachProfile(String coachId) {
		Optional<CoachEntity> coachEntity;
		CoachDTO coachDTO;
		coachEntity=coachrepo.findByCoachId(coachId);
		coachDTO= mapper.map(coachEntity, CoachDTO.class);
		return coachDTO;
	}
	public List<CoachDTO> showAllCoaches() {
		logger.info("In showAllCoaches method");
		List<CoachDTO> coachDTOlist = new ArrayList<>();
		List<CoachEntity> coachEntitylist=new ArrayList<>();
		coachEntitylist = coachrepo.findAll();
		coachDTOlist=coachEntitylist.stream().map(p->mapper.map(p, CoachDTO.class)).collect(Collectors.toList());
		
		return coachDTOlist;
	}
}
