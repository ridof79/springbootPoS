package com.springboot.pos.service;

import com.springboot.pos.dto.SaleDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.model.Sale;
import com.springboot.pos.service.exception.PaymentException;

import java.util.List;

public interface SaleService {

    SaleDto saveQrisTransaction(SaleDto saleDto) throws ServiceException;
    SaleDto saveCashTransaction(SaleDto saleDto) throws ServiceException, PaymentException;
    List<SaleDto> findAllSale();
    SaleDto findSaleById(String id) throws ServiceException;
}
