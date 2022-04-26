package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.entity.University;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.CurrentAccountRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.wrapper.CurrentAccountWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrentAccountService {
    @Autowired
    CurrentAccountRepository currentAccountRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    private CurrentAccount toEntity(CurrentAccountWrapper wrapper) {
        CurrentAccount entity = new CurrentAccount();
        if (wrapper.getAccountTypeId() != null) {
            entity = currentAccountRepository.getById(wrapper.getAccountTypeId());
        }
        Optional<University> optionalUniv = universityRepository.findById(wrapper.getUniversityId());
        University university = optionalUniv.orElse(null);
        entity.setUniversity(university);
        Optional<Bank> optionalBank = bankRepository.findById(wrapper.getBankId());
        Bank bank = optionalBank.orElse(null);
        entity.setBank(bank);
        entity.setAccountNumber(wrapper.getAccountNumber());
        Optional<AccountType> optionalAcc = accountTypeRepository.findById(wrapper.getAccountTypeId());
        AccountType accountType = optionalAcc.orElse(null);
        entity.setAccountType(accountType);
        entity.setInitialBalanceAccount(wrapper.getInitialBalanceAccount());
        entity.setInitialBalanceDate(wrapper.getInitialBalanceDate());
        return entity;
    }

    private CurrentAccountWrapper toWrapper(CurrentAccount entity) {
        CurrentAccountWrapper wrapper = new CurrentAccountWrapper();
        wrapper.setCurrentAccountId(wrapper.getCurrentAccountId());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setAccountNumber(wrapper.getAccountNumber());
        wrapper.setAccountTypeId(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeId() : null);
        wrapper.setAccountTypeName(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeName() : null);
        wrapper.setInitialBalanceAccount(wrapper.getInitialBalanceAccount());
        wrapper.setInitialBalanceDate(wrapper.getInitialBalanceDate());
        return wrapper;
    }

    private List<CurrentAccountWrapper> toWrapperList(List<CurrentAccount> entityList) {
        List<CurrentAccountWrapper> wrapperList = new ArrayList<CurrentAccountWrapper>();
        for (CurrentAccount entity : entityList) {
            CurrentAccountWrapper wrapper = toWrapper(entity);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    public List<CurrentAccountWrapper> findAll() {
        List<CurrentAccount> currentAccountList = currentAccountRepository.findAll();
        return toWrapperList(currentAccountList);
    }
}
