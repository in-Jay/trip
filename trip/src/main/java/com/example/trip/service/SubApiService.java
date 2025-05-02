package com.example.trip.service;

import com.example.trip.dto.NaverSearch;
import com.example.trip.repository.AirplaneRepository;
import com.example.trip.repository.DomesticRepository;
import com.example.trip.repository.HotelRepository;
import com.example.trip.repository.OverseasRepository;
import com.example.trip.subDto.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubApiService {
    private final NaverSearch naverSearch;
    private final OverseasRepository overseasRepository;
    private final DomesticRepository domesticRepository;
    private final AirplaneRepository airplaneRepository;
    private final HotelRepository hotelRepository;

    public List<Overseas> searchOverseas() {
        String result = naverSearch.search("해외여행");
        List<Overseas> items = fromJSONtoOverseas(result);
        //항상 최신 데이터만 overseas 테이블에 저장
        if (!items.isEmpty()) {
            overseasRepository.deleteAll();
            overseasRepository.saveAll(items);
        }
        return items;
    }

    private List<Overseas> fromJSONtoOverseas(String result) {
        return convertJsonToEntity(result, Overseas::new);
    }


    public List<Domestic> searchDomestic() {
        String result = naverSearch.search("국내여행");
        List<Domestic> items = fromJSONtoDomestic(result);
        //항상 최신 데이터만 overseas 테이블에 저장
        if (!items.isEmpty()) {
            domesticRepository.deleteAll();
            domesticRepository.saveAll(items);
        }
        return items;
    }

    private List<Domestic> fromJSONtoDomestic(String result) {
        return convertJsonToEntity(result, Domestic::new);
    }


    public List<Airplane> searchAirplane() {
        String result = naverSearch.search("항공권");
        List<Airplane> items = fromJSONtoAirplane(result);
        //항상 최신 데이터만 overseas 테이블에 저장
        if (!items.isEmpty()) {
            airplaneRepository.deleteAll();
            airplaneRepository.saveAll(items);
        }
        return items;
    }

    private List<Airplane> fromJSONtoAirplane(String result) {
        return convertJsonToEntity(result, Airplane::new);
    }



    public List<Hotel> searchHotel() {
        String result = naverSearch.search("호텔");
        List<Hotel> items = fromJSONtoHotel(result);
        //항상 최신 데이터만 overseas 테이블에 저장
        if (!items.isEmpty()) {
            hotelRepository.deleteAll();
            hotelRepository.saveAll(items);
        }
        return items;
    }

    private List<Hotel> fromJSONtoHotel(String result) {
        return convertJsonToEntity(result, Hotel::new);
    }


    //JSON 변환 공통 메서드
    private <T extends BaseEntity> List<T> convertJsonToEntity(String result, EntitySupplier<T> supplier) {
        //JSONObjet 객체를 사용하여 result 변수의 값을 JSON 객체로 만듦
        JSONObject json = new JSONObject(result);
        JSONArray items = json.getJSONArray("items");
        //총 개수가 정해지지 않은 배열 객체 생성
        List<T> entityList = new ArrayList<>();
        //entityList 배열의 개수만큼 반복
        for (int i = 0; i < items.length(); i++) {
            //JSON 데이터를 itemJson 인스턴스에 저장
            JSONObject itemJson = items.getJSONObject(i);
            //entity 인스턴스로 overseas 크롤링된 데이터가 저장될 것이므로 미리 객체를 생성
            T entity = supplier.get();  //빈 그릇
            //테이블 객체 entity에 title, link 등의 컬럼에 해당하는 데이터를 세팅
            entity.setTitle(Jsoup.parse(itemJson.getString("title")).text());
            entity.setLink(itemJson.getString("link"));
            entity.setImage(itemJson.getString("image"));
            entity.setLprice(itemJson.getInt("lprice"));
            entity.setMallName(itemJson.getString("mallName"));
            entity.setCategory1(itemJson.getString("category1"));
            entity.setCategory2(itemJson.getString("category2"));
            entity.setCategory3(itemJson.getString("category3"));
            entityList.add(entity);
        }
        return entityList;
    }

    //함수형 인터페이스 : 클래스인데 메서드처럼 호출해서 사용함.
    @FunctionalInterface
    private interface EntitySupplier<T> {
        T get();
    }
}
