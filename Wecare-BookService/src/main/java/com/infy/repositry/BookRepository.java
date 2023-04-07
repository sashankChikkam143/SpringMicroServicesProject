package com.infy.repositry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.entity.BookingEntity;


@Repository
public interface BookRepository extends JpaRepository<BookingEntity,Integer>
{
	Optional<BookingEntity> findByUserId(String userId);
	@Query("select b from BookingEntity b where b.userId=:userId and b.appointmentDate>:today")
	List<BookingEntity> findBookingByUserId(@Param("userId")String userId,@Param("today")LocalDate today);
	@Query("select b from BookingEntity b where b.coachId=:coachId and b.appointmentDate>:today")
	List<BookingEntity> findBookingByCoachId(@Param("coachId")String coachId,@Param("today")LocalDate today);
	@Query("select b from BookingEntity b where b.userId=:userId and b.appointmentDate=:appointment and slot=:slot")
	Optional<BookingEntity> findAllBookings(@Param("userId")String userId, @Param("appointment")LocalDate appointmentDate, @Param("slot")String slot);
	BookingEntity findByBookingId(Integer id);

}