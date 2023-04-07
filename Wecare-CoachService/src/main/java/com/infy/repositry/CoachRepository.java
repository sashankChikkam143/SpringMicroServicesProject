package com.infy.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, String> {
	Optional<CoachEntity> findByCoachId(String CoachId);

	
	
	

}
