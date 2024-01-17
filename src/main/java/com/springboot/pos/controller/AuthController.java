package com.springboot.pos.controller;

import com.springboot.pos.dto.AuthDto;
import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.dto.LoginDto;
import com.springboot.pos.dto.SavedCashierDto;
import com.springboot.pos.service.AuthService;
import com.springboot.pos.util.ApiUrlConstant;
import com.springboot.pos.util.Constants;
import com.springboot.pos.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.AUTH_PATH)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto authDto) {
        LoginDto loginDto = authService.login(authDto);
        Response<?> response = new Response<>(Constants.SUCCESSFULLY_LOGIN, loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthDto authDto){
        SavedCashierDto register = authService.register(authDto);
        Response<?> response = new Response<>(Constants.SUCCESSFULLY_CREATE_CASHIER, register);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
