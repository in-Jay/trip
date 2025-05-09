package com.example.trip.controller;

import com.example.trip.dto.Cart;
import com.example.trip.service.CartService;
import com.example.trip.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final FileService fileService;

    @PostMapping("/add/{id}")
    @ResponseBody
    public String addToCart(@PathVariable("id") Long goodsId) {
        cartService.addToCart(goodsId);
        return "장바구니에 추가했습니다.";
    }

    //장바구니의 굿즈목록 보기
    @GetMapping("/list")
    public String viewCart(Model model) {
        //장바구니 전체 목록 가져오기
        List<Cart> cartList = cartService.getAllCart();
        //장바구니의 총금액 구하기
        int totalPrice = cartList.stream().mapToInt(item -> item.getGoodsPrice() * item.getQuantity()).sum();
        //장바구니 목록과 총금액을 cart 템플릿으로 전달
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        return "cart/list";
    }

    //수량 없데이트
    @PostMapping("/update/{cartId}")
    @ResponseBody
    public ResponseEntity<?> updateCartQuantity(@PathVariable("cartId") Long cartId, @RequestBody Map<String, Integer> requestData) {
        int quantity = requestData.get("quantity");
        try {
            cartService.updateCartQuantity(cartId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 수량 업데이트 실패");
        }
    }

    //장바구니 항목 삭제
    @PostMapping("/delete/{cartId}")
    @ResponseBody
    public String deleteCart(@PathVariable("cartId") Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        //cart 이미지 삭제
        if (cart.getGoodsImageUrl() != null) {
            fileService.deleteFile(cart.getGoodsImageUrl());
        }
        cartService.deleteCart(cartId);
        return "장바구니에서 삭제되었습니다.";
    }
}
