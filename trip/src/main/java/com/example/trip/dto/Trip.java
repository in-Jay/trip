package com.example.trip.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //여행상품번호
    private String title; //여행상품제목
    private String imageUrl; //이미지
    private String tag;  //태그
    private String air;  //항공
    private String plan; // 계획, 일정
    private LocalDate startDate; //출발일
    private String personnel; //예약인원
    private double price; //가격

    public Trip(Object o, @NotBlank(message = "여행상품 제목을 입력해 주세요.") String title, String tag, @NotBlank(message = "항공 정보를 입력해 주세요.") String air, String plan, @NotNull(message = "출발 날짜를 입력해 주세요.") LocalDate startDate, @NotBlank(message = "예약 인원을 입력해 주세요") String personnel, @Positive(message = "가격은 양수로 입력해 주세요.") double price) {
    }
}

