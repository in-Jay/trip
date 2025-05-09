package com.example.trip.repository;

import com.example.trip.dto.Item;
import com.example.trip.dto.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemApiRepositoryImpl implements ItemApiRepositoryCustom {
    //EntityManager : Entity를 저장하는 가상의 데이터베이스 객체, Entity를 저장하고 수정, 삭제, 조회하는 등의 모든 관리를 함
    private final EntityManager em;
    //JPAQueryFactory : JPA 패키지를 사용하여 좀 더 쉬운 쿼리문을 작성하기 위한 객체,
    private JPAQueryFactory queryFactory;

    @Override
    public List<Item> findByCategory(String category) {
        if (queryFactory == null) {
            queryFactory = new JPAQueryFactory(em);
        }
        return queryFactory.selectFrom(QItem.item).where(QItem.item.category3.eq(category)).fetch();
    }
}
