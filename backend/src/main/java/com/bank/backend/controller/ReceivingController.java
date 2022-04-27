package com.bank.backend.controller;

import com.bank.backend.entity.Receiving;
import com.bank.backend.service.ReceivingService;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping
public class ReceivingController {
    
    @Autowired
    ReceivingService receivingService;


}
