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

    //get
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public PaginationList<Bank, Bank> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Bank> bankPage = bankRepository.findAll(paging);
        List<Bank> bankList = bankPage.getContent();
        return new PaginationList<Bank, Bank>(bankList, bankPage);
    }

    public Bank findById(Long id){
        Bank bank = bankRepository.findById(id).get();
        return bank;
    }

    public PaginationList<Bank, Bank> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<Bank> bankPage = bankRepository.findByAllCategories(all, paging);
        List<Bank> bankList = bankPage.getContent();
        return new PaginationList<Bank, Bank>(bankList, bankPage);
    }

    //post and put
    public Bank save(Bank bank) {
		if (bank.getBankId() != null) {
			Bank existedBank = bankRepository.getById(bank.getBankId());
			existedBank.setBankName(bank.getBankName());
            existedBank.setCode(bank.getCode());
            existedBank.setImage(bank.getImage());
			return bankRepository.save(existedBank);
		} else {
			return bankRepository.save(bank);
		}
	}

    //delete
    public void delete(Long id){
		bankRepository.deleteById(id);
    }

}
