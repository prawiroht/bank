package com.bank.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.Purchase;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.PurchaseRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PurchaseService {
    
    @Autowired
    PurchaseRepository purchaseRepository;

    // FIND ALL
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    // FIND ALL WITH PAGINATION
    public PaginationList<Purchase, Purchase> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Purchase> purchasePage = purchaseRepository.findAll(paging);
        List<Purchase> purchaseList = purchasePage.getContent();
        return new PaginationList<Purchase, Purchase>(purchaseList, purchasePage);
    }

    // FIND BY ALL CATEGORIES
    public PaginationList<Purchase, Purchase> findByAllCategories(String all, int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Purchase> purchasePage = purchaseRepository.findByAllCategories(all, paging);
        List<Purchase> purchaseList = purchasePage.getContent();
        return new PaginationList<Purchase, Purchase>(purchaseList, purchasePage);
    }

    // GET BY PURCHASE ID
    public Purchase getByPurchaseId(Long purchaseId) {
		Purchase purchase = purchaseRepository.findById(purchaseId).get();
		return purchase;
	}

    // ADD (POST) & UPDATE (PUT)
    public Purchase save(Purchase purchase) {

			return purchaseRepository.save(purchase);
	}

    // DELETE
    public void delete(Long purchaseId) {
		purchaseRepository.deleteById(purchaseId);
	}

}
