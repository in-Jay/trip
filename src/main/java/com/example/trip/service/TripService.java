package com.example.trip.service;

import com.example.trip.dto.Trip;
import com.example.trip.repository.ItemApiRepository;
import com.example.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final ItemApiRepository itemApiRepository;
    private final FileService fileService;

    @Autowired
    public TripService(TripRepository tripRepository, ItemApiRepository itemApiRepository, FileService fileService) {
        this.tripRepository = tripRepository;
        this.itemApiRepository = itemApiRepository;
        this.fileService = fileService;
    }
    //상품 전체 목록 가져오기
    public List<Trip> getAll(){
        return tripRepository.findAll();
    }
    //상품 정보 1개만 가져오기
    public Optional<Trip> getTripById(Long id){
        return tripRepository.findById(id);
    }
    //상품 등록하기
    public Trip createTrip(Trip trip, @RequestParam("file") MultipartFile file) throws IOException {
        //만약 file변수값이 비어있지 않으면 파일 업로드
        if(!file.isEmpty()){
            String fileUrl = fileService.storeFile(file);
            trip.setImageUrl(fileUrl);
        }
        return tripRepository.save(trip);
    }
    //상품 정보 수정하기
    public void modifyTrip(Trip trip) {
        tripRepository.save(trip);
    }
    //여행 상품 삭제
    public void deleteTrip(Long id){
        tripRepository.deleteById(id);
    }

    //특정 날짜에 해당하는 여행 상품 목록 가져오기
    public List<Trip> getTripsByStartDate(LocalDate startDate){
        return tripRepository.findByStartDate(startDate);
    }
}
