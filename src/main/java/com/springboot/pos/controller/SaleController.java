package com.springboot.pos.controller;

import com.springboot.pos.dto.SaleDto;
import com.springboot.pos.exception.ServiceException;
import com.springboot.pos.service.SaleService;
import com.springboot.pos.service.exception.PaymentException;
import com.springboot.pos.util.ApiUrlConstant;
import com.springboot.pos.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.SALE_PATH)
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/qris/add")
    public ResponseEntity<?> addQrisSale(@RequestBody SaleDto saleDto)  {
        try {
            SaleDto savedSaleDto = saleService.saveQrisTransaction(saleDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(savedSaleDto);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @PostMapping("/cash/add")
    public ResponseEntity<?> addCashSale(@RequestBody SaleDto saleDto) {
        try {
            SaleDto savedSaleDto = saleService.saveCashTransaction(saleDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(savedSaleDto);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(e.getMessage()));
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSale() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(saleService.findAllSale());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable String id){
        try {
            SaleDto getSaleDto = saleService.findSaleById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(getSaleDto);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

}
