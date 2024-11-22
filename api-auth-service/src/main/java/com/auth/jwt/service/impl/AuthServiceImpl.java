package com.auth.jwt.service.impl;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.NewUserDto;
import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.AuthUser;
import com.auth.jwt.repository.AuthUserRepository;
import com.auth.jwt.security.JwtProvider;
import com.auth.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public AuthUser save(NewUserDto newUserDto) {

        Optional<AuthUser> user = authUserRepository.findByUserName(newUserDto.getUserName());
        if (user.isPresent()){
            return null;
        }

        String passwordEncode = passwordEncoder.encode(newUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(newUserDto.getUserName())
                .password(passwordEncode)
                .role(newUserDto.getRole())
                .build();

        return authUserRepository.save(authUser);
    }

    @Override
    public TokenDto login(AuthUserDto authUserDto) {

        Optional<AuthUser> authUser = authUserRepository.findByUserName(authUserDto.getUserName());
        if(!authUser.isPresent()){
            return null;
        }

        if(passwordEncoder.matches(authUserDto.getPassword(), authUser.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(authUser.get()));
        }

        return null;
    }

    @Override
    public TokenDto validate(String token, RequestDto requestDto) {

        if(!jwtProvider.validate(token, requestDto)){
            return null;
        }

        String userName = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(userName).isPresent()){
            return null;
        }

        return new TokenDto(token);
    }
}
