package com.example.trip.repository;

import com.example.trip.subDto.Airplane;
import com.example.trip.subDto.Overseas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
