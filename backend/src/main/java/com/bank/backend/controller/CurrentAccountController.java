package com.bank.backend.controller;

import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.service.CurrentAccountService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.CurrentAccountWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/currentaccount")
public class CurrentAccountController {
    @Autowired
    CurrentAccountService currentAccountService;

    @PostMapping(path = "/posts")
    public DataResponse<CurrentAccountWrapper> save(@RequestBody CurrentAccountWrapper wrapper) {
        return new DataResponse<CurrentAccountWrapper>(currentAccountService.save(wrapper));
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<CurrentAccountWrapper> findAll() {
        return new DataResponseList<CurrentAccountWrapper>(currentAccountService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long currentAccountId) {
        currentAccountService.delete(currentAccountId);
    }

    @PutMapping(path = "/update")
    public DataResponse<CurrentAccountWrapper> update(@RequestBody CurrentAccountWrapper wrapper) {
        return new DataResponse<CurrentAccountWrapper>(currentAccountService.save(wrapper));
    }

    @GetMapping(path = "/getAllCategories")
    public DataResponsePagination<CurrentAccountWrapper, CurrentAccount> getByAllCategories(
            @RequestParam("all") String all,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<CurrentAccountWrapper, CurrentAccount>(
                currentAccountService.getAllCategories(all, page, size));
    }
}
