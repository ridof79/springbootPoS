package com.springboot.pos.service.impl;

import com.springboot.pos.dto.UserDetailsDto;
import com.springboot.pos.model.Cashier;
import com.springboot.pos.repository.CashierRepository;
import com.springboot.pos.util.Constants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashierDetailServiceImpl implements UserDetailsService {

    private final CashierRepository cashierRepository;

    public CashierDetailServiceImpl(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        Optional<Cashier> cashier = cashierRepository.findByUsername(username);
        if (cashier.isPresent()) {
            return new UserDetailsDto(cashier.get());
        }
        throw new UsernameNotFoundException(String.format(Constants.CASHIER_NOT_FOUND, "username",username));
    }

}
