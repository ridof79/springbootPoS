package com.springboot.pos.service.impl;

import com.springboot.pos.dto.SaleDto;
import com.springboot.pos.dto.SaleItemDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.model.*;
import com.springboot.pos.repository.SaleRepository;
import com.springboot.pos.service.*;
import com.springboot.pos.service.exception.PaymentException;
import com.springboot.pos.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final PaymentService paymentService;
    private final SaleItemService saleItemService;
    private final CashierService cashierService;
    private final ItemService itemService;

    public SaleServiceImpl(SaleRepository saleRepository, PaymentService paymentService, SaleItemService saleItemService, CashierService cashierService, ItemService itemService) {
        this.saleRepository = saleRepository;
        this.paymentService = paymentService;
        this.saleItemService = saleItemService;
        this.cashierService = cashierService;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public SaleDto saveQrisTransaction(SaleDto saleDto) throws ServiceException {
        Sale sale = convertSale(saleDto);
        sale.setTransactionDate(new Date());
        Payment payment = new QrisPayment();
        Sale savedSale = setupPaymentAndSave(sale, payment);
        return new SaleDto(savedSale);
    }


    @Override
    @Transactional
    public SaleDto saveCashTransaction(SaleDto saleDto) throws ServiceException, PaymentException {
        if(saleDto.getCashInHand() > saleDto.totalPrice()) {
            Sale sale = convertSale(saleDto);
            Payment payment = new CashPayment(saleDto.getCashInHand());
            return new SaleDto(setupPaymentAndSave(sale, payment));
        } else {
            throw new PaymentException(Constants.INSUFFICIENT_CASH);
        }
    }

    @Override
    @Transactional
    public List<SaleDto> findAllSale() {
        return saleRepository.findAll().stream().map(SaleDto::new).collect(Collectors.toList());
    }

    @Override
    public SaleDto findSaleById(String id) throws ServiceException {
        if (saleRepository.findById(id).isPresent()) {
            return new SaleDto(saleRepository.findById(id).get());
        }
        throw new ServiceException(String.format(Constants.SALE_NOT_FOUND, id));
    }

    private SaleItem convertSaleItem(SaleItemDto saleItemDto) {
        SaleItem saleItem = new SaleItem();
        Item item = itemService.findItemByItemCode(saleItemDto.getItem().getItemCode());
        saleItem.setItem(item);
        saleItem.setPrice(item.getPrice());
        saleItem.setQuantity(saleItemDto.getQuantity());
        return saleItem;
    }

    private Sale convertSale(SaleDto saleDto) throws ServiceException {
        Sale sale = new Sale();
        sale.setCashier(cashierService.findCashierByUsername(saleDto.getCashier().getUsername()));

        List<SaleItem> saleItems = saleDto.getSaleItems().stream().map(this::convertSaleItem)
                .peek(saleItem -> saleItem.setSale(sale)).toList();
        for (SaleItem saleItem : saleItems) {
            saleItem.setSale(sale);
        }
        sale.setSaleItems(saleItems);

        return sale;
    }

    private Sale setupPaymentAndSave(Sale sale, Payment payment) {
        payment.setSale(sale);
        double totalPrice = 0;
        for (SaleItem saleItem : sale.getSaleItems()) {
            totalPrice += (saleItem.getPrice() * saleItem.getQuantity());
        }
        payment.setAmount(totalPrice);
        payment.validate();
        sale.setPayment(payment);
        Sale saleResult = saleRepository.save(sale);
        sale.getSaleItems().forEach(saleItemService::save);
        return saleResult;
    }
}
