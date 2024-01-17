package com.springboot.pos.service.impl;

import com.springboot.pos.dto.CashierDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.model.Cashier;
import com.springboot.pos.repository.CashierRepository;
import com.springboot.pos.service.CashierService;
import com.springboot.pos.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;

    public CashierServiceImpl(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    @Transactional
    public CashierDto save(Cashier cashier) {
        return new CashierDto(cashierRepository.save(cashier));
    }

    @Override
    @Transactional
    public CashierDto update(Cashier cashier) {
        return new CashierDto(cashierRepository.save(cashier));
    }

    @Override
    @Transactional
    public CashierDto findCashierById(String id) throws ServiceException {
        if (cashierRepository.findById(id).isPresent()) {
            return new CashierDto(cashierRepository.findById(id).get());
        } else {
            throw new ServiceException(String.format(Constants.CASHIER_NOT_FOUND, "id", id));
        }
    }

    @Override
    @Transactional
    public List<CashierDto> findAll() {
        return cashierRepository.findAll()
                .stream()
                .map(CashierDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Cashier findCashierByUsername(String username) throws ServiceException {
        if (cashierRepository.findCashierByUsername(username) != null) {
            return cashierRepository.findCashierByUsername(username);
        } else {
            throw new ServiceException(String.format(Constants.CASHIER_NOT_FOUND, "username", username));
        }
    }
}
