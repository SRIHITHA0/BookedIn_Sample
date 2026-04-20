package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.dto.LoginDTO;
import com.cts.mfrp.bkin.dto.SignupDTO;
import com.cts.mfrp.bkin.dto.UserInfoDTO;
import com.cts.mfrp.bkin.model.User;
import com.cts.mfrp.bkin.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserInfoDTO userSignup(SignupDTO signupDto) throws AuthenticationException {
        Optional<User> user = userRepository.findByUsername(signupDto.getUsername());
        if(user.isPresent()){
            throw new AuthenticationException("User with username "+signupDto.getUsername()+" already exists");
        }
        System.out.println(signupDto.getPassword());
        User unRegisteredUser = modelMapper.map(signupDto, User.class);
        unRegisteredUser.setPassword(passwordEncoder.encode(unRegisteredUser.getPassword()));
        User registeredUser = userRepository.save(unRegisteredUser);
        return modelMapper.map(registeredUser, UserInfoDTO.class);
    }

    public String userLogin(LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
