package com.example.trip.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String title;
    private LocalDate startDate;
    private BigDecimal totalPrice;
    private int numPeople;
    private String imageUrl;
    @CreationTimestamp
    private LocalDate createDate;
    @ManyToOne
    private Trip trip;
}
