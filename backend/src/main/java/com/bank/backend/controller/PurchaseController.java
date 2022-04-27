package com.bank.backend.controller;

import com.bank.backend.entity.Purchase;
import com.bank.backend.service.PurchaseService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

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
@RequestMapping (path = "/purchase")
public class PurchaseController {
    
    @Autowired
    PurchaseService purchaseService;

    //FIND ALL
    @GetMapping(path = "/findAll")
    public DataResponseList<Purchase> findAll() {
        return new DataResponseList<Purchase>(purchaseService.findAll());
    }

    // FIND ALL WITH PAGINATION
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<Purchase, Purchase> findAllPagination(@RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<Purchase, Purchase>(purchaseService.findAllPagination(page, size));
    }

    // FIND BY ALL CATEGORIES
    @GetMapping(path = "/findByAllCategories")
    public DataResponsePagination<Purchase, Purchase> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam int size){
        return new DataResponsePagination<Purchase, Purchase>(purchaseService.findByAllCategories(all, page, size));
    }

    // GET BY ID
    @GetMapping(path = "/getById")
    public DataResponse<Purchase> getById(@RequestParam("id") Long purchaseId){
        return new DataResponse<Purchase>(purchaseService.getByPurchaseId(purchaseId));
    }

    // POST / ADD
    @PostMapping(path="/post")
	public DataResponse<Purchase> save(@RequestBody Purchase purchase) {
		return new DataResponse<Purchase>(purchaseService.save(purchase));
	}

    // PUT / EDIT
    @PutMapping(path = "/put")
    public DataResponse<Purchase> update(@RequestBody Purchase purchase) {
        return new DataResponse<Purchase>(purchaseService.save(purchase));
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long purchaseId) {
        purchaseService.delete(purchaseId);
    }

}
