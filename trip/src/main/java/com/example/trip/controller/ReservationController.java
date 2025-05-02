package com.example.trip.controller;

import com.example.trip.dto.Item;
import com.example.trip.dto.Reservation;
import com.example.trip.dto.Trip;
import com.example.trip.service.FileService;
import com.example.trip.service.ItemService;
import com.example.trip.service.ReservationService;
import com.example.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ItemService itemService;
    private final TripService tripService;
    private final ReservationService reservationService;
    private final FileService fileService;

    @GetMapping("/booking/{id}")
    public String getReservationPage(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "reservation/booking";
    }

    //예약하기 템플릿으로 여행상품 정보 넘기기
    @GetMapping("/reserve/{id}")
    public String showAddPage(Model model, @PathVariable("id") Long id) {
        Trip trip = tripService.getTripById(id).orElseThrow(()->new IllegalArgumentException("여행 상품이 존재하지 않습니다."));
        model.addAttribute("trip", trip);
        return "reservation/reserve";
    }
    //여행상품정보에서 예약하기를 클릭하면 reservation 테이블에 예약정보 저장
    @PostMapping("/reserve/{id}")
    public String addReservation(
            Model model,
            @PathVariable("id") Long id,
            @RequestParam("startDate") String startDate,
            @RequestParam("numPeople") int numPeople,
            @RequestParam("totalPrice") double totalPrice
    ) {
        Trip trip = tripService.getTripById(id).orElseThrow(()->new IllegalArgumentException("여행 상품이 존재하지 않습니다."));
        //예약 정보를 reservation 테이블에 저장
        Reservation reservation = new Reservation();
        reservation.setTrip(trip);
        reservation.setTitle(trip.getTitle());
        reservation.setStartDate(LocalDate.parse(startDate));
        reservation.setNumPeople(numPeople);
        reservation.setTotalPrice(BigDecimal.valueOf(totalPrice));
        reservationService.saveReservation(reservation);
        //예약 내역 페이지로 이동
        return "redirect:/reservation/view";

    }

    //예약 내역 보기
    @GetMapping("/view")
    public String viewReservations(Model model) {
        List<Reservation> reservationList = reservationService.getAllReservation();
        //예약 내역에 이미지 추가
        for (Reservation reservation : reservationList) {
            //만약 이미지가 없고, 예약 내역이 있으면 이미지를 추가함
            if (reservation.getImageUrl() == null && reservation.getTrip() != null) {
                reservation.setImageUrl(reservation.getTrip().getImageUrl());
            }
        }
        model.addAttribute("reservationList", reservationList);
        return "reservation/view";
    }

    //예약 취소
    @PostMapping("/cancel/{id}")
    @ResponseBody
    public String cancelReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        reservationService.cancelReservation(id);
        //만약 예약 내역에 이미지가 었으면 이미지 파일을 삭제
        if (reservation.getImageUrl() != null) {
            fileService.deleteFile(reservation.getTrip().getImageUrl());
        }
        return "예약이 취소되었습니다";
    }
}
