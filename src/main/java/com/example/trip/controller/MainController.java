package com.example.trip.controller;

import com.example.trip.dto.Board;
import com.example.trip.dto.Item;
import com.example.trip.service.BoardService;
import com.example.trip.service.ItemApiService;
import com.example.trip.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final ItemApiService itemApiService;
    private final ItemService itemService;
    private final BoardService boardService;

    @GetMapping("/")
    public String index (Model model) {
        //API를 통해 각 카테고리별 데이터 가져오기
        List<Item> tripList = itemApiService.searchItem("여행");

        //최근 게시글 4개 가져오기
        List<Board> recentBoard = boardService.getPosts(4);

        //배열 선언
        List<Item> tripItem1 = new ArrayList<>();
        List<Item> tripItem2 = new ArrayList<>();
        List<Item> tripItem3 = new ArrayList<>();
        List<Item> tripItem4 = new ArrayList<>();

        if (tripList.size() > 5) {
            tripItem1 = tripList.subList(0, 8);
            tripItem2 = tripList.subList(8, 17);
            tripItem3 = tripList.subList(17, 25);
            tripItem4 = tripList.subList(25, 32);
        } else {
            tripItem1 = tripList;
            tripItem2 = tripList;
            tripItem3 = tripList;
            tripItem4 = tripList;
        }


        model.addAttribute("tripItem1", tripItem1);
        model.addAttribute("tripItem2", tripItem2);
        model.addAttribute("tripItem3", tripItem3);
        model.addAttribute("tripItem4", tripItem4);
        model.addAttribute("recentBoard", recentBoard);

        return "index";
    }
}
