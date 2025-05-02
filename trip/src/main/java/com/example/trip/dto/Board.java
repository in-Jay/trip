package com.example.trip.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")      //긴 문장 허용
    private String content;
    private LocalDate createdAt;
    private LocalDate modifyAt;
    private int count;
    private String username;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.modifyAt = LocalDate.now();
        this.count = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifyAt = LocalDate.now();
    }
}
