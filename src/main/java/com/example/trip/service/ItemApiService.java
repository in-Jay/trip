package com.example.trip.service;

import com.example.trip.dto.Item;
import com.example.trip.dto.NaverSearch;
import com.example.trip.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemApiService {
    private final NaverSearch naverSearch;
    private final ItemRepository itemRepository;

    public List<Item> searchItem(String query) {
        //naverSearch 클래스의 search() 메서드를 호출하면서 검색어(query)를 매개변수로 전달
        String result = naverSearch.search(query);
        //검색어로 검색한 결과를 fromJSONtoItems 메서드로 전달
        //frmJSONtoItems 메서드에서 실행한 결과를 item 테이블에 저장
        List<Item> items = fromJSONtoItems(result);
        //만약 item 테이블에 데이터가 있으면 기존 데이터를 item 테이블에서 삭제한 다음 최신 데이터를 저장
        if (!items.isEmpty()) {
            itemRepository.deleteAll();
            itemRepository.saveAll(items);
        }
        return items;
    }

    //JSON 형식의 응답 문자열을 ItemApi 클래스 형식의 배열로 변환하는 메서드
    public List<Item> fromJSONtoItems(String result) {
        //JSONObject : 매개변수 result로 전달받은 문자열을 json 데이터로 변환하는 객체 (key, value 속성을 가지고 있다.)
        JSONObject json = new JSONObject(result);
        //json 데이터에서 key 값으로 json 배열을 만들어서 items에 저장
        JSONArray items = json.getJSONArray("items");
        //key, value가 없는 일반 배열 선언
        List<Item> itemList = new ArrayList<>();
        //json 배열의 데이터 개수만큼 반복
        for (int i = 0; i < items.length(); i++) {
            //items 배열의 데이터를 하나씩 가져와서 itemJson에 저장
            JSONObject itemJson = items.getJSONObject(i);
            //HTML 태그 제거
            String title = Jsoup.parse(itemJson.getString("title")).text();
            //itemJson에 저장된 json 데이터에서 title, link, image, lprice, mallName, brand, category1, category2, category3, category4 정보만 itemApi에 저장
            Item item = new Item();
            item.setTitle(title);
            item.setLink(itemJson.getString("link"));
            item.setImage(itemJson.getString("image"));
            item.setLprice(itemJson.getInt("lprice"));
            item.setMallName(itemJson.getString("mallName"));
            item.setCategory1(itemJson.getString("category1"));
            item.setCategory2(itemJson.getString("category2"));
            item.setCategory3(itemJson.getString("category3"));
            //itemList 배열에 itemApi 객체 추가
            itemList.add(item);
        }
        return itemList;
    }
}
