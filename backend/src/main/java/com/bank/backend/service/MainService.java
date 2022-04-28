package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.Fund;
import com.bank.backend.entity.Main;
import com.bank.backend.entity.Purchase;
import com.bank.backend.entity.Status;
import com.bank.backend.entity.University;
import com.bank.backend.entity.User;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.FundRepository;
import com.bank.backend.repository.MainRepository;
import com.bank.backend.repository.PurchaseRepository;
import com.bank.backend.repository.StatusRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.MainWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MainService {
    @Autowired
    MainRepository mainRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired 
    AccountTypeRepository accountTypeRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    FundRepository fundRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;

    private Main toEntity(MainWrapper wrapper) {
        Main entity = new Main();
        if (wrapper.getMainId() != null) {
            entity = mainRepository.getById(wrapper.getMainId());
        }
        University university = universityRepository.getById(wrapper.getUniversityId());
        entity.setUniversity(university);
        Bank bank = bankRepository.getById(wrapper.getBankId());
        entity.setBank(bank);
        entity.setAccountNumber(wrapper.getAccountNumber());
        entity.setMutationId(wrapper.getMutationId());
        entity.setTransactionDate(wrapper.getTransactionDate());
        entity.setValue(wrapper.getValue());
        Purchase purchase = purchaseRepository.getById(wrapper.getPurchaseId());
        entity.setPurchase(purchase);
        AccountType accountType = accountTypeRepository.getById(wrapper.getAccountTypeId());
        entity.setAccountType(accountType);
        Fund fund = fundRepository.getById(wrapper.getFundId());
        entity.setFund(fund);
        entity.setDescription(wrapper.getDescription());
        User user = userRepository.getById(wrapper.getUserId());
        entity.setUser(user);
        Status status =  statusRepository.getById(wrapper.getStatusId());
        entity.setStatus(status);
        return entity;
    }

    private MainWrapper toWrapper(Main entityMain) {
        MainWrapper wrapper = new MainWrapper();
        wrapper.setMainId(entityMain.getMainId());
        wrapper.setUniversityId(entityMain.getUniversity() != null ? entityMain.getUniversity().getUniversityId() : null);
        wrapper.setUniversityName(entityMain.getUniversity() != null ? entityMain.getUniversity().getUniversityName() : null);
        wrapper.setBankId(entityMain.getBank() != null ? entityMain.getBank().getBankId() : null);
        wrapper.setCode(entityMain.getBank() != null ? entityMain.getBank().getCode() : null);
        wrapper.setBankName(entityMain.getBank() != null ? entityMain.getBank().getBankName() : null);
        wrapper.setAccountNumber(entityMain.getAccountNumber());
        wrapper.setMutationId(entityMain.getMutationId());
        wrapper.setTransactionDate(entityMain.getTransactionDate());
        wrapper.setValue(entityMain.getValue());
        wrapper.setPurchaseId(entityMain.getPurchase() != null ? entityMain.getPurchase().getPurchaseId() : null);
        wrapper.setPurchaseName(entityMain.getPurchase() != null ? entityMain.getPurchase().getPurchaseName() : null);
        wrapper.setAccountTypeId(entityMain.getAccountType() != null ? entityMain.getAccountType().getAccountTypeId() : null);
        wrapper.setAccountTypeName(entityMain.getAccountType() != null ? entityMain.getAccountType().getAccountTypeName() : null);
        wrapper.setFundId(entityMain.getFund() != null ? entityMain.getFund().getFundId() : null);
        wrapper.setFundName(entityMain.getFund() != null ? entityMain.getFund().getFundName() : null);
        wrapper.setDescription(entityMain.getDescription());
        wrapper.setUserId(entityMain.getUser() != null ? entityMain.getUser().getUserId() : null);
        wrapper.setUsername(entityMain.getUser() != null ? entityMain.getUser().getUsername() : null);
        wrapper.setStatusId(entityMain.getStatus() != null ? entityMain.getStatus().getStatusId() : null);
        wrapper.setStatusName(entityMain.getStatus() != null ? entityMain.getStatus().getStatusName() : null);
        return wrapper;
    }

    private List<MainWrapper> toWrapperList(List<Main> entityList) {
        List<MainWrapper> wrapperList = new ArrayList<MainWrapper>();
        for (Main entity : entityList) {
            MainWrapper wrapper = toWrapper(entity);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    /* Retrieve single item */
    public MainWrapper findById(Long mainId) {
        Main main = mainRepository.getById(mainId);
        return toWrapper(main);
    }

    /* Retrieve All Data */
    public List<MainWrapper> findAll() {
        List<Main> mainList = mainRepository.findAll();
        return toWrapperList(mainList);
    }

    public PaginationList<MainWrapper, Main> toPaginationList(Page<Main> entityPage) {
        List<Main> entityList = entityPage.getContent();
        List<MainWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<MainWrapper, Main> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }

    //find All Pagination
    public PaginationList<MainWrapper, Main> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(mainRepository.findAll(paging));
    }
//Find by All Categories
    public PaginationList<MainWrapper, Main> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<Main> mainPage = mainRepository.getByAllCategories(all, paging);
        List<Main> mainList = mainPage.getContent();
        List<MainWrapper> mainWrapperList = toWrapperList(mainList);
        return new PaginationList<MainWrapper, Main>(mainWrapperList, mainPage);
    }


    public PaginationList<MainWrapper, Main> findByResquestStatus(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Status status = statusRepository.getById(1L);
        return toPaginationList(mainRepository.findByStatus(status, paging));
    }

/* Create and Update */
public MainWrapper save(MainWrapper wrapper) {
        return toWrapper(mainRepository.save(toEntity(wrapper)));
    } 
/* Delete Data */
public void delete(Long id) {
    mainRepository.deleteById(id);
}

}
