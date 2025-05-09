package com.example.trip.service;

import com.example.trip.dto.Member;
import com.example.trip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Member create(String username, String password, String email) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        memberRepository.save(member);
        return member;
    }

    //중복 검사
    public boolean checkUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    //사용자 아이디를 인식하여 로그인 했는지 검사하는 메서드
    public Member getMember(String username) {
        Optional<Member> member = memberRepository.findByUsername(username);
        return member.get();
    }

    //아이디 찾기
    public String findUsernameByEamil(String email) {
        //Optional 객체 사용할 떄 map 메서드는 email이 존재하면 그 email에 해당하는 사용자 아이디를 꺼내와서 리턴하고, email이 존재하지 않으면 null 값을 리턴
        return memberRepository.findByEmail(email).map(Member::getUsername).orElse(null);
    }

    //비밀번호 재설정
    public boolean resetPassword(String username, String email, String newPassword) {
        //Member 테이블에서 사용자 아이디와 이메일 모두 일치하는 데이터를 찾아서 memberOpt에 저장
        Optional<Member> memberOpt = memberRepository.findByUsernameAndEmail(username, email);
        //만약 memberOpt가 존재하면 그 데이터를 가져와서 member 인스턴스에 저장
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            //새 비밀번호를 암호화해서 Member 테이블에 저장할 수 있도록 세팅
            member.setPassword(passwordEncoder.encode(newPassword));
            //Member 테이블에 사용자 아이디, 이메일과 새 비밀번호를 저장
            memberRepository.save(member);
            return true;
        }
        //memberOpt가 비어있으면 false를 리턴
        return false;
    }
}
