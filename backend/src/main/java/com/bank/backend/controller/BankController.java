package com.bank.backend.controller;

import com.bank.backend.entity.Bank;
import com.bank.backend.service.BankService;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/bank")
public class BankController {
    @Autowired
    BankService bankService;

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Bank, Bank> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<Bank, Bank>(bankService.findAllPagination(page, size));
    };

}
