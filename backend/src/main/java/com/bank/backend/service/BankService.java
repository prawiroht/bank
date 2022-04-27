package com.bank.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import com.bank.backend.entity.Bank;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    BankRepository bankRepository;

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public PaginationList<Bank, Bank> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Bank> bankPage = bankRepository.findAll(paging);
        List<Bank> bankList = bankPage.getContent();
        return new PaginationList<Bank, Bank>(bankList, bankPage);
    }

    

}
