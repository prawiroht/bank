package com.bank.backend.controller;

import com.bank.backend.entity.AccountType;
import com.bank.backend.service.AccountTypeService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/accountType")
public class AccountTypeController {
    @Autowired
    AccountTypeService accountTypeService;
    
    @GetMapping (path = "/findAll")
    public DataResponseList<AccountType> findAll(){
        return new DataResponseList<AccountType>(accountTypeService.findAll());
    }

    @GetMapping (path = "/findByBankId")
    public DataResponse<AccountType> findById(Long id){
        return new DataResponse<AccountType>(accountTypeService.findByAccountTypeId(id));
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<AccountType, AccountType> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<AccountType, AccountType>(accountTypeService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<AccountType, AccountType> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<AccountType, AccountType>(accountTypeService.findAllCategories(all, page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<AccountType> post(@RequestBody AccountType accountType){
        return new DataResponse<AccountType>(accountTypeService.save(accountType));
    }

    @PutMapping (path = "/put")
    public DataResponse<AccountType> put(@RequestBody AccountType accountType){
        return new DataResponse<AccountType>(accountTypeService.save(accountType));
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<AccountType> delete(@RequestParam("id") Long id){
        accountTypeService.delete(id);
            return new DataResponse<AccountType>(true, "Delete Sukses");
    }

}
