package com.example.trip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordReset {
    private String username;
    private String email;
    private String newPassword;
}
