package com.bank.backend.controller;

import javax.persistence.Table;

import com.bank.backend.entity.Investation;
import com.bank.backend.service.InvestationService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.InvestationWrapper;

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

@CrossOrigin
@RestController
@RequestMapping(path = "/investment")
public class InvestationController {
    @Autowired
    InvestationService investationService;

    @PostMapping(path = "/posts")
    public DataResponse<InvestationWrapper> save(@RequestBody InvestationWrapper wrapper) {
        return new DataResponse<InvestationWrapper>(investationService.save(wrapper));
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<InvestationWrapper> findAll() {
        return new DataResponseList<InvestationWrapper>(investationService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long InvestationId) {
        investationService.delete(InvestationId);
    }

    @PutMapping(path = "/update")
    public DataResponse<InvestationWrapper> update(@RequestBody InvestationWrapper wrapper) {
        return new DataResponse<InvestationWrapper>(investationService.save(wrapper));
    }

    // @GetMapping(path = "/getAllCategories")
    // public DataResponsePagination<InvestationWrapper, Investation>
    // getByAllCategories(
    // @RequestParam("all") String all,
    // @RequestParam("page") int page, @RequestParam("size") int size) {
    // return new DataResponsePagination<InvestationWrapper, Investation>(
    // InvestationService.getAllCategories(all, page, size));
    // }
}
