package com.auth.jwt.service;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.NewUserDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.AuthUser;
import org.antlr.v4.runtime.Token;

public interface AuthService {

    AuthUser save(NewUserDto newUserDto);

    TokenDto login(AuthUserDto authUserDto);

    TokenDto validate(String token);

}
