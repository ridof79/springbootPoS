package com.springboot.pos.service;

import com.springboot.pos.dto.AuthDto;
import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.dto.LoginDto;
import com.springboot.pos.dto.SavedCashierDto;

public interface AuthService {
    LoginDto login(AuthDto user);
    SavedCashierDto register(AuthDto user);

}
