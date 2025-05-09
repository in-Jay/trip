package com.example.trip.service;

import com.example.trip.dto.Item;
import com.example.trip.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    //Item 저장
    public void saveItems(List<Item> items) {
        itemRepository.saveAll(items);
    }

    //카테고리별 데이터 가져오기
    public List<Item> getItemsByCategory(String category2) {
        return itemRepository.findAll();
    }

    //booking.html 템플릿에 정보 내보내기
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("여행 상품 정보가 없습니다."));
    }
}
