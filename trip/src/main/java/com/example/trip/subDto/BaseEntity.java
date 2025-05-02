package com.example.trip.subDto;


import lombok.Data;
//abstract: 추상클래스, 반드시 상속받아야 하는 클래스
//추상클래스는 만들었으면 무조건 어딘가에서는 써야함 - 꼭 다른클래스가 상속받게하려고! 중요한 기능인데 혹시빼먹었을까봐
@Data
public abstract class BaseEntity {
    private String title;
    private String link;
    private String image;
    private int lprice;
    private String mallName;
    private String category1;
    private String category2;
    private String category3;
}
