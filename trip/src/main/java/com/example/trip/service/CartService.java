package com.example.trip.service;

import com.example.trip.dto.Cart;
import com.example.trip.dto.Goods;
import com.example.trip.repository.CartRepository;
import com.example.trip.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;

    //장바구니에 추가
    public void addToCart(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(()->new IllegalArgumentException("상품을 찾울 수 없습니다."));
        Cart cart = new Cart();
        cart.setGoodsCode(goods.getGoodsCode());
        cart.setGoodsName(goods.getGoodsName());
        cart.setGoodsPrice(goods.getGoodsPrice());
        cart.setQuantity(1);
        cart.setGoodsImageUrl(goods.getGoodsImageUrl());
        cartRepository.save(cart);
    }

    //장바구니의 목록 보기
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    //장바구니의 수량 수정하기
    public void updateCartQuantity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->new IllegalArgumentException("장바구니 항목이 없습니다."));
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    //id 값으로 1개의 굿즈 정보 가져오기
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("장바구니 항목을 찾을 수 없습니다."));
    }

    //장바구니에서 삭제
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
