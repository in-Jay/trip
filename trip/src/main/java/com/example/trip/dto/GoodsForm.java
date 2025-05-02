package com.example.trip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsForm {
    @NotBlank(message = "상품코드를 입력해 주세요.")
    private String goodsCode;

    @NotBlank(message = "상품명을 입력해 주세요.")
    @Size(max = 100, message = "상품명은 최대 100자까지 입력가능합니다.")
    private String goodsName;
    @Min(value = 1, message = "기본 수량은 1 이상이어야 합니다.")
    private int baseQuantity;
    @Min(value = 1, message = "최소 수량은 1 이상이어야 합니다.")
    private int minQuantity;
    @Positive(message = "가격은 양수로 입력해 주세요.")
    private int goodsPrice;
    @NotBlank(message = "규격을 입력해 주세요.")
    private String goodsSize;
    @NotBlank(message = "재질을 입력해 주세요.")
    private String material;
    @NotBlank(message = "제작 기간을 입력해 주세요.")
    private String productionTime;
    @NotBlank(message = "원산지를 입력해 주세요.")
    private String goodsOrigin;
    private String goodsImageUrl;


}
