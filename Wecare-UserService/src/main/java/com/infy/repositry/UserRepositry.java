package com.infy.repositry;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.infy.entity.UserEntity;

public interface UserRepositry extends JpaRepository<UserEntity,String>{
	@Query("select u from UserEntity u where u.userId=:userId")
	Optional<UserEntity> findByUserId(@Param("userId")String userId);
}
