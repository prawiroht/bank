package com.bank.backend.controller;

import java.util.Date;

import com.bank.backend.entity.Investation;
import com.bank.backend.service.InvestationService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.InvestationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

    @GetMapping(path = "/findById")
    public DataResponse<InvestationWrapper> findById(@RequestParam Long id) {
        try {
            InvestationWrapper hasil = investationService.getInvestationById(id);
            return new DataResponse<InvestationWrapper>(hasil);
        } catch (Exception e) {
            return new DataResponse<InvestationWrapper>(false, "Current Account not found with id: " + id);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long InvestationId) {
        investationService.delete(InvestationId);
    }

    @PutMapping(path = "/update")
    public DataResponse<InvestationWrapper> update(@RequestBody InvestationWrapper wrapper) {
        return new DataResponse<InvestationWrapper>(investationService.save(wrapper));
    }

    @GetMapping(path = "/getAllCategories")
    public DataResponsePagination<InvestationWrapper, Investation> getByAllCategories(
            @RequestParam("all") String all,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<InvestationWrapper, Investation>(
                investationService.findAllCategoriesWithPagination(all, page, size));
    }

    @GetMapping(path = "/getTotalInvestment")
    public Long getTotalInvestment() {
        return investationService.sumNominalWithStatusApprove();
    }

    @GetMapping(path = "/getTotalInvestmentWithParam")
    public Long getTotalInvestmentWithParam(@RequestParam @DateTimeFormat(iso = ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endDate,
            @RequestParam Long bankId) {
        return investationService.sumNominalWithParam(startDate, endDate, bankId);
    }

    @GetMapping(path = "/findByRequestStatus")
    public DataResponsePagination<InvestationWrapper, Investation> findByRequestStatus(int page, int size){
        return new DataResponsePagination<InvestationWrapper, Investation>(investationService.findByRequestStatus(page, size));
    }
}
