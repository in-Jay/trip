package com.example.trip.controller;

import com.example.trip.service.SubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SubController {
    private final SubApiService subApiService;

    @GetMapping("/sub/overseas")
    public String overseas(Model model) {
        model.addAttribute("items", subApiService.searchOverseas());
        return "sub/overseas";
    }

    @GetMapping("/sub/domestic")
    public String domestic(Model model) {
        model.addAttribute("items", subApiService.searchDomestic());
        return "sub/domestic";
    }

    @GetMapping("/sub/airplane")
    public String airplane(Model model) {
        model.addAttribute("items", subApiService.searchAirplane());
        return "sub/airplane";
    }

    @GetMapping("/sub/hotel")
    public String hotel(Model model) {
        model.addAttribute("items", subApiService.searchHotel());
        return "sub/hotel";
    }
}
