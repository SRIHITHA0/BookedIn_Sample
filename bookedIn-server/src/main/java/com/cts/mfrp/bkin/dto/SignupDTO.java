package com.cts.mfrp.bkin.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupDTO {
    private String username;
    private String email;
    private String password;
}
