package com.springboot.pos.service;

import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.model.Cashier;

import java.util.List;

public interface CashierService {

    CashierDto save(Cashier cashier);
    CashierDto update(Cashier cashier);
    CashierDto findCashierById(String id) throws ServiceException;
    List<CashierDto> findAll();
    Cashier findCashierByUsername(String username) throws ServiceException;
}
