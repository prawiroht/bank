package com.bank.backend.controller;

import com.bank.backend.entity.Fund;
import com.bank.backend.service.FundService;
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
@RequestMapping (path = "/fund")
public class FundController {
    @Autowired
    FundService fundService;

    @GetMapping (path = "/findAll")
    public DataResponseList<Fund> findAll(){
        return new DataResponseList<Fund>(fundService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Fund, Fund> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<Fund, Fund>(fundService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<Fund, Fund> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<Fund, Fund>(fundService.findAllCategories(all, page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<Fund> post(@RequestBody Fund fund){
        return new DataResponse<Fund>(fundService.save(fund));
    }

    @PutMapping (path = "/put")
    public DataResponse<Fund> put(@RequestBody Fund fund){
        return new DataResponse<Fund>(fundService.save(fund));
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<Fund> delete(@RequestParam("id") Long id){
        fundService.delete(id);
            return new DataResponse<Fund>(true, "Delete Success");
    }
}
