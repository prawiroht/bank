package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.Fund;
import com.bank.backend.entity.Main;
import com.bank.backend.entity.Purchase;
import com.bank.backend.entity.University;
import com.bank.backend.entity.User;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.FundRepository;
import com.bank.backend.repository.MainRepository;
import com.bank.backend.repository.PurchaseRepository;
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

    private Main toEntity(MainWrapper wrapper) {
        Main entity = new Main();
        if (wrapper.getMainId() != null) {
            entity = mainRepository.getById(wrapper.getMainId());
        }
        Optional<University> optionalUniv = universityRepository.findById(wrapper.getUniversityId());
        University university = optionalUniv.orElse(null);
        entity.setUniversity(university);
        Optional<Bank> optionalBank = bankRepository.findById(wrapper.getBankId());
        Bank bank = optionalBank.orElse(null);
        entity.setBank(bank);
        entity.setAccountNumber(wrapper.getAccountNumber());
        entity.setMutationId(wrapper.getMutationId());
        entity.setTransactionDate(wrapper.getTransactionDate());
        entity.setValue(wrapper.getValue());
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(wrapper.getPurchaseId());
        Purchase purchase = optionalPurchase.orElse(null);
        entity.setPurchase(purchase);
        Optional<AccountType> optionalAcc = accountTypeRepository.findById(wrapper.getAccountTypeId());
        AccountType acc = optionalAcc.orElse(null);
        entity.setAccountType(acc);
        Optional<Fund> optionalFund = fundRepository.findById(wrapper.getFundId());
        Fund fund = optionalFund.orElse(null);
        entity.setFund(fund);
        entity.setDescription(wrapper.getDescription());
        Optional<User> optionalUser = userRepository.findById(wrapper.getUserId());
        User user = optionalUser.orElse(null);
        entity.setUser(user); 
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
    public MainWrapper getByMainId(Long mainId) {
        Main main = mainRepository.findById(mainId).get();
        return toWrapper(main);
    }

    /* Retrieve All Data */
    public List<MainWrapper> findAll() {
        List<Main> mainList = mainRepository.findAll();
        return toWrapperList(mainList);
    }

    /* Find All With Pagination */
    public PaginationList<MainWrapper, Main> findAllPagination(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Main> mainPage = mainRepository.findAll(paging);
        List<Main> mainList = mainPage.getContent();
        List<MainWrapper> mainWrapperList = toWrapperList(mainList);
        return new PaginationList<MainWrapper, Main>(mainWrapperList, mainPage);
    }
//Find by All Categories
    public PaginationList<MainWrapper, Main> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<Main> mainPage = mainRepository.getByAllCategories(all, paging);
        List<Main> mainList = mainPage.getContent();
        List<MainWrapper> mainWrapperList = toWrapperList(mainList);
        return new PaginationList<MainWrapper, Main>(mainWrapperList, mainPage);
    }
/* Create and Update */
public MainWrapper save(MainWrapper wrapper) {
    Main main = new Main();
    if (wrapper.getMainId() == null) {
        if (wrapper.getUniversityId() == null) {
            throw new BusinessException("University Id can't be null");
        }
        if (wrapper.getBankId() == null) {
            throw new BusinessException("Bank Id can't be null");
        }
        main = mainRepository.save(toEntity(wrapper));
    } else {
        main = mainRepository.save(toEntity(wrapper));
    }
    return toWrapper(main);
}

/* Delete Data */
public void delete(Long id) {
    if (id == null) {
        throw new BusinessException("Employee Id does not exist");
    }
    Optional<Main> main = mainRepository.findById(id);
    if (!main.isPresent()) {
        throw new BusinessException("Main Id does not found");
    }
    mainRepository.deleteById(id);
}

}
