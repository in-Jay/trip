package com.example.trip.repository;

import com.example.trip.subDto.Domestic;
import com.example.trip.subDto.Overseas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomesticRepository extends JpaRepository<Domestic, Long> {
}
