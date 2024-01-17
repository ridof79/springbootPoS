package com.springboot.pos.service.impl;

import com.springboot.pos.config.JwtUtils;
import com.springboot.pos.dto.AuthDto;
import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.dto.LoginDto;
import com.springboot.pos.dto.UserDetailsDto;
import com.springboot.pos.model.Cashier;
import com.springboot.pos.service.AuthService;
import com.springboot.pos.service.CashierService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final CashierService cashierService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, CashierService cashierService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.cashierService = cashierService;
    }

    @Override
    public LoginDto login(AuthDto user) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        UserDetailsDto userDetails = (UserDetailsDto) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        return new LoginDto(userDetails, token);
    }

    @Override
    public CashierDto register(AuthDto user) {
        Cashier cashier = new Cashier(
                user.getUsername(),
                user.getName(),
                passwordEncoder.encode(user.getPassword()));
        return cashierService.save(cashier);
    }
}
