package com.example.trip.controller;

import com.example.trip.dto.MemberForm;
import com.example.trip.dto.PasswordReset;
import com.example.trip.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register(MemberForm memberForm) {
        return "member/register";
    }
    @PostMapping("/register")
    public String register(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/register";
        }
        if (!memberForm.getPassword1().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "member/register";
        }
        memberService.create(memberForm.getUsername(), memberForm.getPassword1(), memberForm.getEmail());
        return "redirect:/member/login";
    }

    //아이디 중복검사
    @GetMapping("/username_check")
    public String checkUsername(@RequestParam("username") String  username, Model model) {
        boolean exists = memberService.checkUsername(username);
        if (exists) {
            model.addAttribute("message", "이미 사용중인 아이디입니다.");
        } else {
            model.addAttribute("message", "사용 가능한 아이디입니다.");
        }
        return "member/username_check";
    }

    //로그인
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    //로그인할 때 사용자 정보 확인
    @GetMapping("/username_exists")
    @ResponseBody
    public boolean checkUsernameExists(@RequestParam("username") String username) {
        return memberService.checkUsername(username);
    }

    //아이디 찾기
    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }
    @PostMapping("/find_id")
    @ResponseBody
    public String findId(@RequestParam("email") String email) {
        String username = memberService.findUsernameByEamil(email);
        return username != null ? "고객님의 아이디는 " + username + "입니다." : "일치하는 아이디가 없습니다.";
    }

    //비밀번호 재설정
    @GetMapping("/reset_pass")
    public String resetPass() {
        return"member/reset_pass";
    }
    @PostMapping("/reset_pass")
    @ResponseBody
    public String resetPass(@RequestBody PasswordReset request) {
        boolean success = memberService.resetPassword(request.getUsername(), request.getEmail(), request.getNewPassword());
        return success ? "비밀번호가 성공적으로 변경되었습니다." : "아이디와 이메일이 일치하지 않습니다.";
    }

}
