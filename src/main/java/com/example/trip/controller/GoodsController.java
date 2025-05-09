package com.example.trip.controller;

import com.example.trip.dto.Goods;
import com.example.trip.dto.GoodsForm;
import com.example.trip.service.FileService;
import com.example.trip.service.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final FileService fileService;

    @GetMapping("/list")
    public String listGoods(Model model) {
        List<Goods> goodsList = goodsService.getAllGoods();
        model.addAttribute("goodsList", goodsList);
        return "goods/list";
    }

    //여행용 굿즈 추가하기
    @GetMapping("/add")
    public String addGoods(GoodsForm goodsForm) {
        return "goods/add";
    }
    @PostMapping("/add")
    public String addGoods(@Valid GoodsForm goodsForm, BindingResult bindingResult, @RequestParam("image") MultipartFile image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "goods/add";
        }
        //이미지 파일 저장
        if (!image.isEmpty()) {
            String imageUrl = fileService.storeFile(image);
            goodsForm.setGoodsImageUrl(imageUrl);
        }
        goodsService.saveGoods(goodsForm);
        return "redirect:/goods/list";
    }

    //여행용 굿즈 정보 수정하기
    @GetMapping("/edit/{id}")
    public String editGoods(@PathVariable("id") Long id, Model model) {
        GoodsForm goodsForm = new GoodsForm();
        goodsService.getGoodsById(id);
        model.addAttribute("goodsForm", goodsForm);
        return "goods/add";
    }
    @PostMapping("/edit/{id}")
    public String editGoods(@PathVariable("id") Long id, @Valid GoodsForm goodsForm, BindingResult bindingResult, @RequestParam("image") MultipartFile image) throws IOException {
        if (bindingResult.hasErrors()) {
            return "goods/add";
        }
        if (!image.isEmpty()) {
            String imageUrl = fileService.storeFile(image);
            goodsForm.setGoodsImageUrl(imageUrl);
        }
        goodsService.editGoods(id, goodsForm);
        return "redirect:/goods/list";
    }

    //여행용 굿즈 정보 삭제
    @GetMapping("/delete/{id}")
    public String deleteGoods(@PathVariable("id") Long id) {
        Goods goods = goodsService.getGoodsById(id);
        //굿즈 이미지 삭제
        if (goods.getGoodsImageUrl() != null) {
            fileService.deleteFile(goods.getGoodsImageUrl());
        }
        goodsService.deleteGoods(id);
        return "redirect:/goods/list";
    }
}
