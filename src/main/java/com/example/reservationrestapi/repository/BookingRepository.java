package com.example.reservationrestapi.repository;

import com.example.reservationrestapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

}
