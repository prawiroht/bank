package com.bank.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountTypeService {
    @Autowired
    AccountTypeRepository accountTypeRepository;

   //get
   public List<AccountType> findAll() {
    return accountTypeRepository.findAll();
    }

    public AccountType findByAccountTypeId(Long id){
        return accountTypeRepository.findById(id).get();
    }

    public PaginationList<AccountType, AccountType> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<AccountType> accountTypePage = accountTypeRepository.findAll(paging);
        List<AccountType> accountTypeList = accountTypePage.getContent();
        return new PaginationList<AccountType, AccountType>(accountTypeList, accountTypePage);
    }

    public AccountType findById(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID");
        Optional<AccountType> accountType = accountTypeRepository.findById(id);
        if (!accountType.isPresent())
            throw new BusinessException("ID "+ id +" is not found.");
        return accountType.get();
    }

    public PaginationList<AccountType, AccountType> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<AccountType> accountTypePage = accountTypeRepository.findByAllCategories(all, paging);
        List<AccountType> accountTypeList = accountTypePage.getContent();
        return new PaginationList<AccountType, AccountType>(accountTypeList, accountTypePage);
    }

    //post and put
    public AccountType save(AccountType accountType) {
        if (accountType.getAccountTypeId() != null) {
            AccountType existedAccountType = accountTypeRepository.getById(accountType.getAccountTypeId());
            existedAccountType.setAccountTypeName(accountType.getAccountTypeName());
            return accountTypeRepository.save(existedAccountType);
        } else {
            return accountTypeRepository.save(accountType);
        }
    }

    //delete
    public void delete(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID.");
        Optional<AccountType> accountType = accountTypeRepository.findById(id);
        if (!accountType.isPresent())
            throw new BusinessException("Account Type ID "+ id +" is not found.");
        accountTypeRepository.deleteById(id);
    }
}
