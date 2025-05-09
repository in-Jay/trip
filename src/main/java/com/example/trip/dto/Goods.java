package com.example.trip.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goodsCode;
    private String goodsName;
    private int baseQuantity;
    private int minQuantity;
    private int goodsPrice;
    private String goodsSize;
    private String material;
    private String productionTime;
    private String goodsOrigin;
    private String goodsImageUrl;
}
