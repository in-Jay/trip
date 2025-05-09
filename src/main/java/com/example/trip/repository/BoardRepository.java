package com.example.trip.repository;

import com.example.trip.dto.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //c
    Page<Board> findByOrderByIdDesc (Pageable pageable);
}
