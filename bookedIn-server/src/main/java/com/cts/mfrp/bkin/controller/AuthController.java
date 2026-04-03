package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.dto.LoginDTO;
import com.cts.mfrp.bkin.dto.SignupDTO;
import com.cts.mfrp.bkin.dto.UserInfoDTO;
import com.cts.mfrp.bkin.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserInfoDTO> userSignup(@RequestBody SignupDTO userDto) throws AuthenticationException {
        return ResponseEntity.ok(authService.userSignup(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDto, HttpServletResponse httpServletResponse){
        String token = authService.userLogin(loginDto);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
