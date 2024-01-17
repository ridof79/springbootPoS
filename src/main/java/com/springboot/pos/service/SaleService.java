package com.springboot.pos.service;

import com.springboot.pos.dto.SaleDto;
import com.springboot.pos.dto.SavedSaleDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.model.Sale;
import com.springboot.pos.service.exception.PaymentException;

import java.util.List;

public interface SaleService {

    SavedSaleDto saveQrisTransaction(SaleDto saleDto) throws ServiceException;
    SavedSaleDto saveCashTransaction(SaleDto saleDto) throws ServiceException, PaymentException;
    List<SaleDto> findAllSale();
    SavedSaleDto findSaleById(String id) throws ServiceException;
}
