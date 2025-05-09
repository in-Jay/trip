package com.example.trip.dto;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverSearch {
    //application.properties에서 선언한 외부변수의 값을 가져오기
    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.secret}")
    private  String clientSecret;

    //멤버메서드 선언
    public String search(String query) {
        //RestTemplate : REST방식의 api를 호출하는 객체, 비동기 접근방식을 지원함. REST방식(@GetMapping, @PostMapping, @DeleteMapping, @UpdateMapping)
        RestTemplate rest = new RestTemplate();

        //HttpHeaders : Http 헤더정보 읽어오기
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", clientId);
        headers.add("X-Naver-Client-Secret", clientSecret);

        //HttpEntity : HttpHeader 정보 + HttpBody 정보
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + query + "&display=32";

        //ResponseEntity : HttpEntity로 요청한 데이터(HttpHeader + HttpBody)를 포함하고, HttpRequest에 대한 응답 데이터를 포함하는 클래스
        ResponseEntity<String> responseEntity = rest.exchange(apiURL, HttpMethod.GET, requestEntity, String.class);

        return responseEntity.getBody();
    }
}
