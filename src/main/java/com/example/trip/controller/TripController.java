package com.example.trip.controller;

import com.example.trip.dto.Item;
import com.example.trip.dto.Trip;
import com.example.trip.dto.TripForm;
import com.example.trip.service.FileService;
import com.example.trip.service.ItemApiService;
import com.example.trip.service.ItemService;
import com.example.trip.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private FileService fileService;

    //전체 데이터 불러오기
    @GetMapping("/list")
    public String listTrips(Model model) {
        List<Trip> trips = tripService.getAll();
        model.addAttribute("trips", trips);
        return "trip/list";
    }

    //여행상품 등록하기
    @GetMapping("/add")
    public String addTrip(TripForm tripForm) {
        return "trip/add";
    }
    @PostMapping("/add")
    public String addTrip(@Valid TripForm tripForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "trip/add";
        }
        Trip trip = new Trip();
        trip.setTitle(tripForm.getTitle());
        trip.setTag(tripForm.getTag());
        trip.setAir(tripForm.getAir());
        trip.setPlan(tripForm.getPlan());
        trip.setStartDate(tripForm.getStartDate());
        trip.setPersonnel(tripForm.getPersonnel());
        trip.setPrice(tripForm.getPrice());

        MultipartFile file = tripForm.getFile();
        tripService.createTrip(trip, file);
        return "redirect:/trip/list";
    }

    //출발날짜에 해당하는 여행상품 목록과 item 목록을 템플릿으로 반환하는 메서드
    @GetMapping("/tripsByDate")
    public String tripsByDate(@RequestParam("date") String date, @RequestParam("itemId") Long itemId, Model model) {
        //날짜 데이터를 LocalDate 형식으로 변환
        LocalDate localDate = LocalDate.parse(date);
        //출발 날짜에 맞는 여행상품 목록 가져오기
        List<Trip> trips = tripService.getTripsByStartDate(localDate);
        //item 데이터를 가져오기
        Item item = itemService.getItemById(itemId);

        model.addAttribute("trips", trips);
        model.addAttribute("selectedDate", localDate);
        model.addAttribute("item", item);
        return "reservation/booking";
    }

    //id 값으로 1개의 여행상품 상세정보 가져오기
    @GetMapping("/detail/{id}")
    public String tripDetail(@PathVariable("id") Long id, Model model) {
        Trip trip = tripService.getTripById(id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        model.addAttribute("trip", trip);
        return "trip/detail";
    }

    //상품 정보 수정
    @GetMapping("/modify/{id}")
    public String modifyTrip(TripForm tripForm, @PathVariable("id") Long id) {
        //IllegalArgumentException : 호출자가 인수로 부적절한 값을 넘길 때 예외처리하는 객체
        Trip trip = tripService.getTripById(id).orElseThrow(()->new IllegalArgumentException("상품이 존재하지 않습니다."));
        tripForm.setTitle(trip.getTitle());
        tripForm.setTag(trip.getTag());
        tripForm.setAir(trip.getAir());
        tripForm.setPlan(trip.getPlan());
        tripForm.setStartDate(trip.getStartDate());
        tripForm.setPersonnel(trip.getPersonnel());
        tripForm.setPrice(trip.getPrice());
        return "trip/add";
    }
    @PostMapping("/modify/{id}")
    public String modifyTrip(@Valid TripForm tripForm, BindingResult bindingResult, @PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            return "trip/add";
        }
        Trip trip = tripService.getTripById(id).orElseThrow(()-> new IllegalArgumentException("상품 정보가 없습니다."));
        trip.setTitle(tripForm.getTitle());
        trip.setTag(tripForm.getTag());
        trip.setAir(tripForm.getAir());
        trip.setPlan(tripForm.getPlan());
        trip.setStartDate(tripForm.getStartDate());
        trip.setPersonnel(tripForm.getPersonnel());
        trip.setPrice(tripForm.getPrice());
        if(!file.isEmpty()){
            String fileUrl = fileService.storeFile(file);
            trip.setImageUrl(fileUrl);
        }
        tripService.modifyTrip(trip);
        return "redirect:/trip/detail/" + id ;
    }

    //여행 정보 삭제
    @GetMapping("/delete/{id}")
    public String deleteTrip1(@PathVariable("id") Long id) {
        return "/trip/detail/${id}";
    }
    //@ResponseBody : "여행 상품이 삭제되었습니다." 문자열을 템플릿으로 직접 전송하기 위한 어노테이션
    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteTrip2(@PathVariable("id") Long id) {
        //DB의 trip 테이블에서 id에 해당하는 데이터 삭제
        tripService.deleteTrip(id);
        return "여행 상품이 삭제되었습니다.";
    }


}
