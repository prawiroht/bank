package com.bank.backend.controller;

import com.bank.backend.entity.Receiving;
import com.bank.backend.service.ReceivingService;
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
@RequestMapping
public class ReceivingController {
    
    @Autowired
    ReceivingService receivingService;

    @GetMapping (path = "/findAll")
    public DataResponseList<Receiving> findAll(){
        return new DataResponseList<Receiving>(receivingService.findAll());
    }

    @GetMapping (path = "/findByBankId")
    public DataResponse<Receiving> findById(Long id){
        return new DataResponse<Receiving>(receivingService.findByReceivingId(id));
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Receiving, Receiving> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<Receiving, Receiving>(receivingService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<Receiving, Receiving> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<Receiving, Receiving>(receivingService.findAllCategories(all, page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<Receiving> post(@RequestBody Receiving receiving){
        return new DataResponse<Receiving>(receivingService.save(receiving));
    }

    @PutMapping (path = "/put")
    public DataResponse<Receiving> put(@RequestBody Receiving receiving){
        return new DataResponse<Receiving>(receivingService.save(receiving));
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<Receiving> delete(@RequestParam("id") Long id){
        receivingService.delete(id);
            return new DataResponse<Receiving>(true, "Delete Sukses");
    }

}
