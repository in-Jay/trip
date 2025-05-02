package com.example.trip.repository;

import com.example.trip.subDto.Hotel;
import com.example.trip.subDto.Overseas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
