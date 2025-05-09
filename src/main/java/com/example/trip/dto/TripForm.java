package com.example.trip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class TripForm {
    @NotBlank(message = "여행상품 제목을 입력해 주세요.")
    private String title;
    private String tag;

    @NotBlank(message = "항공 정보를 입력해 주세요.")
    private String air;
    private String plan;

    @NotNull(message = "출발 날짜를 입력해 주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotBlank(message = "예약 인원을 입력해 주세요")
    private String personnel;

    //Positive : 양수의~
    @Positive(message = "가격은 양수로 입력해 주세요.")
    private double price;
    private MultipartFile file;     //이미지 파일

}
