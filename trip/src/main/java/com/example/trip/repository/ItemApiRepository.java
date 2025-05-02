package com.example.trip.repository;

import com.example.trip.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemApiRepository extends JpaRepository<Item, Long>, ItemApiRepositoryCustom {


}
