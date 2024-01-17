package com.springboot.pos.service.impl;

import com.springboot.pos.model.SaleItem;
import com.springboot.pos.repository.SaleItemRepository;
import com.springboot.pos.service.SaleItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    @Transactional
    public void save(SaleItem saleItem) {
        saleItemRepository.save(saleItem);
    }
}
