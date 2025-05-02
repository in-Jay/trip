package com.example.trip.repository;

import com.example.trip.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //아이디 존재하는지 검사
    boolean existsByUsername(String username);
    //member 테이블에서 아이디 찾기
    Optional<Member> findByUsername(String username);
    //member 테이블에서 이메일 찾기
    Optional<Member> findByEmail(String email);
    //비밀번호 재설정할 때 아이디와 비밀번호를 찾아서 일치하는 데이터가 있어야 함.
    Optional<Member> findByUsernameAndEmail(String username, String email);
}
