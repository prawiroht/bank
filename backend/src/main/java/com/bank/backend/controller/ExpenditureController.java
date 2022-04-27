package com.bank.backend.controller;

import com.bank.backend.entity.Expenditure;
import com.bank.backend.service.ExpenditureService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.ExpenditureWrapper;

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
@RequestMapping (path = "/expenditure")
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;

    @GetMapping (path = "/findAll")
    public DataResponseList<ExpenditureWrapper> findAll(){
        return new DataResponseList<ExpenditureWrapper>(expenditureService.findAll());
    }

    @GetMapping (path = "/findByBankId")
    public DataResponse<ExpenditureWrapper> findById(@RequestParam("id") Long id){
        ExpenditureWrapper expenditure = expenditureService.findByExpenditureId(id);
        if (expenditure == null)
            return new DataResponse<ExpenditureWrapper>(false, "Data not found");
        return new DataResponse<ExpenditureWrapper>(expenditure);
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<ExpenditureWrapper, Expenditure> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<ExpenditureWrapper, Expenditure> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findAllCategories(all, page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<ExpenditureWrapper> post(@RequestBody ExpenditureWrapper wrapper){
        return new DataResponse<ExpenditureWrapper>(expenditureService.save(wrapper));
    }

    @PutMapping (path = "/put")
    public DataResponse<ExpenditureWrapper> put(@RequestBody ExpenditureWrapper wrapper){
        return new DataResponse<ExpenditureWrapper>(expenditureService.save(wrapper));
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<ExpenditureWrapper> delete(@RequestParam("id") Long id){
        expenditureService.delete(id);
            return new DataResponse<ExpenditureWrapper>(true, "Delete Sukses");
    }


}
