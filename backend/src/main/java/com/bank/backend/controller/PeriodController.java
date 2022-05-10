package com.bank.backend.controller;

import com.bank.backend.entity.Period;
import com.bank.backend.service.PeriodService;
import com.bank.backend.util.DataResponseList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/period")
public class PeriodController {
    @Autowired
    PeriodService periodService;

    // FIND ALL
    @GetMapping(path = "/findAll")
    public DataResponseList<Period> findAll() {
        return new DataResponseList<>(periodService.findAll());
    }
}
