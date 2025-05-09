package com.example.trip.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 아이디를 입력해 주세요.")
    private String username;
    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인을 입력해 주세요.")
    private String password2;
    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email
    private String email;
}
