package com.bank.backend.controller;


import com.bank.backend.entity.Main;
import com.bank.backend.service.MainService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.MainWrapper;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping (path = "/main")
public class MainController {
    @Autowired
    MainService mainService;

    @GetMapping (path = "/findAll")
    public DataResponseList<MainWrapper> findAll(){
        return new DataResponseList<MainWrapper>(mainService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<MainWrapper, Main> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<MainWrapper, Main>(mainService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<MainWrapper, Main> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<MainWrapper, Main>(mainService.findAllCategories(all, page, size));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long mainId) {
        mainService.delete(mainId);
    }

    @PostMapping("/post")
    public DataResponse<MainWrapper> save(@RequestBody MainWrapper wrapper) {
        return new DataResponse<MainWrapper>(mainService.save(wrapper));
    }

    @PutMapping("/update")
    public DataResponse<MainWrapper> update(@RequestBody MainWrapper wrapper) {
        return new DataResponse<MainWrapper>(mainService.save(wrapper));
    }
}
