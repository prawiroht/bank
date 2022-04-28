package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.entity.Investation;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.CurrentAccountRepository;
import com.bank.backend.repository.StatusRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;

    // util
    private CurrentAccount toEntity(CurrentAccountWrapper wrapper) {
        CurrentAccount entity = new CurrentAccount();
        if (wrapper.getCurrentAccountId() != null) {
            entity = currentAccountRepository.getById(wrapper.getAccountTypeId());
        }
        entity.setUniversity(universityRepository.getById(wrapper.getUniversityId()));
        entity.setBank(bankRepository.getById(wrapper.getBankId()));
        entity.setAccountNumber(wrapper.getAccountNumber());
        entity.setAccountType(accountTypeRepository.getById(wrapper.getAccountTypeId()));
        entity.setInitialBalanceAccount(wrapper.getInitialBalanceAccount());
        entity.setInitialBalanceDate(wrapper.getInitialBalanceDate());
        entity.setStatus(statusRepository.getById(wrapper.getStatusId()));
        entity.setUser(userRepository.getById(wrapper.getUserId()));
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
        wrapper.setStatusId(entity.getStatus() != null ? entity.getStatus().getStatusId() : null);
        wrapper.setStatusName(entity.getStatus() != null ? entity.getStatus().getStatusName() : null);
        wrapper.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        wrapper.setUserName(entity.getUser() != null ? entity.getUser().getUsername() : null);
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

    private PaginationList<CurrentAccountWrapper, CurrentAccount> toPaginationList(Page<CurrentAccount> entityPage) {
        List<CurrentAccount> entityList = entityPage.getContent();
        List<CurrentAccountWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<CurrentAccountWrapper, CurrentAccount> paginationList = new PaginationList<>(wrapperList,
                entityPage);
        return paginationList;
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
        return toPaginationList(currentAccountRepository.findAll(PageRequest.of(page, size)));
    }

    // Retrieve list of data with pagination with param all category
    public PaginationList<CurrentAccountWrapper, CurrentAccount> getAllCategories(String all, int page, int size) {
        return toPaginationList(currentAccountRepository.getByAllCategories(all, PageRequest.of(page, size)));
    }

    // Retieve list of data with pagination with status requested
    public PaginationList<CurrentAccountWrapper, CurrentAccount> findByResquestStatus(int page, int size) {
        return toPaginationList(
                currentAccountRepository.findByStatus(statusRepository.getById(1L), PageRequest.of(page, size)));
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
