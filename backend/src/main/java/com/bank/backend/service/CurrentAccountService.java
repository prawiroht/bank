package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.CurrentAccountRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.CurrentAccountWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    // util
    private CurrentAccount toEntity(CurrentAccountWrapper wrapper) {
        CurrentAccount entity = new CurrentAccount();
        if (wrapper.getCurrentAccountId() != null) {
            Optional<CurrentAccount> currentAccount = currentAccountRepository.findById(wrapper.getAccountTypeId());
            if (!currentAccount.isPresent())
                throw new BusinessException("Current Account not found: " + wrapper.getAccountTypeId() + '.');
            entity = currentAccount.get();
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
        wrapper.setCurrentAccountId(entity.getCurrentAccountId());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setUniversityName(entity.getUniversity() != null ? entity.getUniversity().getUniversityName() : null);
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setCode(entity.getBank() != null ? entity.getBank().getCode() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setAccountNumber(entity.getAccountNumber());
        wrapper.setAccountTypeId(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeId() : null);
        wrapper.setAccountTypeName(
                entity.getAccountType() != null ? entity.getAccountType().getAccountTypeName() : null);
        wrapper.setInitialBalanceAccount(entity.getInitialBalanceAccount());
        wrapper.setInitialBalanceDate(entity.getInitialBalanceDate());
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

    // Retrieve list of data
    public List<CurrentAccountWrapper> findAll() {
        List<CurrentAccount> currentAccountList = currentAccountRepository.findAll();
        return toWrapperList(currentAccountList);
    }

    // Retrieve single data
    public CurrentAccountWrapper getCurrentAccountById(Long currentAccountId) {
        return toWrapper(currentAccountRepository.findById(currentAccountId).get());
    }

    // Retrieve list of data with pagination
    public PaginationList<CurrentAccountWrapper, CurrentAccount> findAllWithPagination(int page,
            int size) {
        Page<CurrentAccount> currentAccountPage = currentAccountRepository.findAll(PageRequest.of(page, size));
        List<CurrentAccount> currentAccountList = currentAccountPage.getContent();
        List<CurrentAccountWrapper> currentAccountWrappers = toWrapperList(currentAccountList);
        return new PaginationList<CurrentAccountWrapper, CurrentAccount>(currentAccountWrappers,
                currentAccountPage);
    }

    // Retrieve list of data with pagination with param all category
    public PaginationList<CurrentAccountWrapper, CurrentAccount> getAllCategories(String all, int page, int size) {
        Page<CurrentAccount> currentAccountPage = currentAccountRepository.getByAllCategories(all,
                PageRequest.of(page, size));
        List<CurrentAccount> currentAccountList = currentAccountPage.getContent();
        List<CurrentAccountWrapper> currentAccountWrappers = toWrapperList(currentAccountList);
        return new PaginationList<CurrentAccountWrapper, CurrentAccount>(currentAccountWrappers, currentAccountPage);
    }

    // Create and Update
    public CurrentAccountWrapper save(CurrentAccountWrapper wrapper) {
        CurrentAccount currentAccount = currentAccountRepository.save(toEntity(wrapper));
        return toWrapper(currentAccount);
    }

    // Delete
    public void delete(Long id) {
        if (id == null)
            throw new BusinessException("Id cannot be null");
        Optional<CurrentAccount> entity = currentAccountRepository.findById(id);
        if (!entity.isPresent())
            throw new BusinessException("Cannot found Current Account with id :" + id + ".");
        currentAccountRepository.deleteById(id);
    }
}
