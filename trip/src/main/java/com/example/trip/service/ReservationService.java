package com.example.trip.service;

import com.example.trip.dto.Reservation;
import com.example.trip.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TripService tripService;

    //reservation 테이블에 예약내역 저장
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    //모든 예약 내역 가져오기
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    //예약 내역 1개 가져오기
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("예약 내역이 없습니다."));
    }

    //예약 취소
    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
