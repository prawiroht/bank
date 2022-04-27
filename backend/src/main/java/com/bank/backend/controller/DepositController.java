package com.bank.backend.controller;

import com.bank.backend.service.DepositService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.wrapper.DepositWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/deposit")
public class DepositController {
    @Autowired
    DepositService depositService;

    @PostMapping(path = "/posts")
    public DataResponse<DepositWrapper> save(@RequestBody DepositWrapper wrapper) {
        return new DataResponse<DepositWrapper>(depositService.save(wrapper));
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<DepositWrapper> findAll() {
        return new DataResponseList<DepositWrapper>(depositService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long depositId) {
        depositService.delete(depositId);
    }

    @PutMapping(path = "/update")
    public DataResponse<DepositWrapper> update(@RequestBody DepositWrapper wrapper) {
        return new DataResponse<DepositWrapper>(depositService.save(wrapper));
    }
}
