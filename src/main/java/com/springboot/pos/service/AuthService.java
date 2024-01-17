package com.springboot.pos.service;

import com.springboot.pos.dto.AuthDto;
import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.dto.LoginDto;

public interface AuthService {
    LoginDto login(AuthDto user);
    CashierDto register(AuthDto user);

}
