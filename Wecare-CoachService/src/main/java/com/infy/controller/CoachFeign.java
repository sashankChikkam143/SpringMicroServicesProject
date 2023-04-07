package com.infy.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.DTO.BookingDTO;

@FeignClient(name="BookService",url="http://localhost:8100/")
public interface CoachFeign {
	@RequestMapping("/booking/{CoachId}")
	 List<BookingDTO> findBookingByCoachId(@PathVariable("CoachId") String CoachId);

}
