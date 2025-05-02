package com.example.trip.repository;

import com.example.trip.dto.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    //startDate로 여행상품 목록 조회
    //booking.html 템플릿에서 달력의 날짜를 클릭하면 날짜에 등록된 여행 상품 목록을
    List<Trip> findByStartDate(LocalDate startDate);
}
