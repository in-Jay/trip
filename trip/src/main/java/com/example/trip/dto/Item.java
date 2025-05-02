package com.example.trip.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String link;
    private String image;
    private int lprice;

    @Column(nullable = true)    //null 허용
    private String mallName;

    @Column(nullable = true)
    private String category1;
    @Column(nullable = true)
    private String category2;
    @Column(nullable = true)
    private String category3;

    //Json 데이터를 받아서 멤버변수에 저장하는 생성자
    public Item(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
        this.mallName = itemJson.getString("mallName");
        this.category1 = itemJson.getString("category1");
        this.category2 = itemJson.getString("category2");
        this.category3 = itemJson.getString("category3");
    }
}
