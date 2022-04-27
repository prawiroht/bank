package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.Expenditure;
import com.bank.backend.entity.Fund;
import com.bank.backend.entity.Purchase;
import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.ExpenditureRepository;
import com.bank.backend.repository.FundRepository;
import com.bank.backend.repository.PurchaseRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.ExpenditureWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenditureService {
    @Autowired
    ExpenditureRepository expenditureRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    FundRepository fundRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    AccountTypeRepository accountTypeRepository;

    //util
    private Expenditure toEntity(ExpenditureWrapper wrapper){
        Expenditure entity = new Expenditure();
        if(wrapper.getExpenditureId() != null){
            Optional<Expenditure> expenditure = expenditureRepository.findById(wrapper.getExpenditureId());
            if (!expenditure.isPresent())
                throw new BusinessException("Expenditure ID "+ wrapper.getExpenditureId() +" is not found.");
            entity=expenditure.get();
        }
        Optional<Bank> optBank = bankRepository.findById(wrapper.getBankId());
        Bank bank = optBank.orElse(null);
        entity.setBank(bank);
        Optional<University> optUniversity= universityRepository.findById(wrapper.getUniversityId());
        University university = optUniversity.orElse(null);
        entity.setUniversity(university);
        entity.setAccountNumber(wrapper.getAccountNumber());
        entity.setMutationId(wrapper.getMutationId());
        entity.setTransactionDate(wrapper.getTransactionDate());
        entity.setValue(wrapper.getValue());
        Optional<Purchase> optPurchase = purchaseRepository.findById(wrapper.getPurchaseId());
        Purchase purchase = optPurchase.orElse(null);
        entity.setPurchase(purchase);
        Optional<AccountType> optAccountType = accountTypeRepository.findById(wrapper.getAccountTypeId());
        AccountType accountType = optAccountType.orElse(null);
        entity.setAccountType(accountType);
        Optional<Fund> optFund = fundRepository.findById(wrapper.getFundId());
        Fund fund = optFund.orElse(null);
        System.out.println(fund);
        entity.setFund(fund);
        entity.setDescription(wrapper.getDescription());
        return entity;
    }

    private ExpenditureWrapper toWrapper(Expenditure entity){
        ExpenditureWrapper wrapper = new ExpenditureWrapper();
        wrapper.setExpenditureId(entity.getExpenditureId());
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setCode(entity.getBank() != null ? entity.getBank().getCode() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setUniversityName(entity.getUniversity() != null ? entity.getUniversity().getUniversityName() : null);
        wrapper.setAccountNumber(entity.getAccountNumber());
        wrapper.setMutationId(entity.getMutationId());
        wrapper.setTransactionDate(entity.getTransactionDate());
        wrapper.setValue(entity.getValue());
        wrapper.setPurchaseId(entity.getPurchase() != null ? entity.getPurchase().getPurchaseId() : null);
        wrapper.setPurchaseAlias(entity.getPurchase() != null ? entity.getPurchase().getAlias() : null);
        wrapper.setPurchaseName(entity.getPurchase() != null ? entity.getPurchase().getPurchaseName() : null);
        wrapper.setAccountTypeId(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeId() : null);
        wrapper.setAccountTypeName(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeName() : null);
        wrapper.setFundId(entity.getFund() != null ? entity.getFund().getFundId() : null);
        wrapper.setFundAlias(entity.getFund() != null ? entity.getFund().getAlias() : null);
        wrapper.setFundName(entity.getFund() != null ? entity.getFund().getFundName() : null);
        wrapper.setDescription(entity.getDescription());
        return wrapper;
    }

    private List<ExpenditureWrapper> toWrapperList(List<Expenditure> entityList){
        List<ExpenditureWrapper> wrapperList = new ArrayList<ExpenditureWrapper>();
        for (Expenditure entity : entityList) {
            wrapperList.add(toWrapper(entity));
        }
        return wrapperList;
    }

    private PaginationList<ExpenditureWrapper, Expenditure> toPaginationList(Page<Expenditure> entityPage){
        List<Expenditure> entityList = entityPage.getContent();
        List<ExpenditureWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<ExpenditureWrapper, Expenditure> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }

    //get
    public List<ExpenditureWrapper> findAll() {
    return toWrapperList(expenditureRepository.findAll());
    }

    public ExpenditureWrapper findByExpenditureId(Long id){
        return toWrapper(expenditureRepository.findById(id).get());
    }

    public PaginationList<ExpenditureWrapper, Expenditure> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(expenditureRepository.findAll(paging));
    }

    public Expenditure findById(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID");
        Optional<Expenditure> expenditure = expenditureRepository.findById(id);
        if (!expenditure.isPresent())
            throw new BusinessException("ID "+ id +" is not found.");
        return expenditure.get();
    }

    public PaginationList<ExpenditureWrapper, Expenditure> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        return toPaginationList(expenditureRepository.findByAllCategories(all, paging));
    }

    //post and put
    public ExpenditureWrapper save(ExpenditureWrapper wrapper){
        return toWrapper(expenditureRepository.save(toEntity(wrapper)));
    }

    //delete
    public void delete(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID.");
        Optional<Expenditure> expenditure = expenditureRepository.findById(id);
        if (!expenditure.isPresent())
            throw new BusinessException("Expenditure ID "+ id +" is not found.");
        expenditureRepository.deleteById(id);
    }
}
