package com.example.trip.service;

import com.example.trip.dto.Goods;
import com.example.trip.dto.GoodsForm;
import com.example.trip.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    //id 값으로 1개의 상품 정보 가져오기
    public Goods getGoodsById(Long id) {
        return goodsRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
    }

    //템플릿에서 입력받은 상품정보를 goods 테이블에 저장
    public void saveGoods(GoodsForm goodsForm) {
        Goods goods = new  Goods();
        goods.setGoodsCode(goodsForm.getGoodsCode());
        goods.setGoodsName(goodsForm.getGoodsName());
        goods.setBaseQuantity(goodsForm.getBaseQuantity());
        goods.setMinQuantity(goodsForm.getMinQuantity());
        goods.setGoodsPrice(goodsForm.getGoodsPrice());
        goods.setGoodsSize(goodsForm.getGoodsSize());
        goods.setMaterial(goodsForm.getMaterial());
        goods.setProductionTime(goodsForm.getProductionTime());
        goods.setGoodsOrigin(goodsForm.getGoodsOrigin());
        goods.setGoodsImageUrl(goodsForm.getGoodsImageUrl());
        goodsRepository.save(goods);

    }

    //여행용 굿즈 정보 수정
    public void editGoods(Long id, GoodsForm goodsForm) {
        //매개변수 id에 해당하는 상품 찾아서 goods 인스턴스에 저장
        Goods goods = getGoodsById(id);
        goods.setGoodsCode(goodsForm.getGoodsCode());
        goods.setGoodsName(goodsForm.getGoodsName());
        goods.setBaseQuantity(goodsForm.getBaseQuantity());
        goods.setMinQuantity(goodsForm.getMinQuantity());
        goods.setGoodsPrice(goodsForm.getGoodsPrice());
        goods.setGoodsSize(goodsForm.getGoodsSize());
        goods.setMaterial(goodsForm.getMaterial());
        goods.setProductionTime(goodsForm.getProductionTime());
        goods.setGoodsOrigin(goodsForm.getGoodsOrigin());
        goods.setGoodsImageUrl(goodsForm.getGoodsImageUrl());
        goodsRepository.save(goods);
    }

    //여행용 굿즈 삭제
    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }
}
