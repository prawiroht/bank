package com.bank.backend.controller;

import com.bank.backend.entity.Bank;
import com.bank.backend.service.BankService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping (path = "/bank")
public class BankController {
    @Autowired
    BankService bankService;

    @GetMapping (path = "/findAll")
    public DataResponseList<Bank> findAll(){
        return new DataResponseList<Bank>(bankService.findAll());
    }

    @GetMapping (path = "/findById")
    public DataResponse<Bank> findById(Long id){
        try {
            return new DataResponse<Bank>(bankService.findById(id));
        } catch (Exception e) {
            return new DataResponse<Bank>(false, "Data not found: "+ id);
        }
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Bank, Bank> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<Bank, Bank>(bankService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<Bank, Bank> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<Bank, Bank>(bankService.findAllCategories(all, page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<Bank> post(@RequestBody Bank bank){
        return new DataResponse<Bank>(bankService.save(bank));
    }

    @PutMapping (path = "/put")
    public DataResponse<Bank> put(@RequestBody Bank bank){
        try {
            return new DataResponse<Bank>(bankService.save(bank));
        } catch (Exception e) {
            return new DataResponse<Bank>(false, "Data not found: "+ bank.getBankId());
        }
        
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<Bank> delete(@RequestParam("id") Long id){
        try {
            bankService.delete(id);
            return new DataResponse<Bank>(true, "Delete Successful");
        } catch (Exception e) {
            return new DataResponse<Bank>(false, "Data not found: "+ id);
        }
        
    }

}
