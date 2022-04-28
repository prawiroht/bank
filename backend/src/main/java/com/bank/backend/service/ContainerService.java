package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.AccountType;
import com.bank.backend.entity.Bank;
import com.bank.backend.entity.Container;
import com.bank.backend.entity.Fund;
import com.bank.backend.entity.Purchase;
import com.bank.backend.entity.Status;
import com.bank.backend.entity.University;
import com.bank.backend.entity.User;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.AccountTypeRepository;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.ContainerRepository;
import com.bank.backend.repository.FundRepository;
import com.bank.backend.repository.PurchaseRepository;
import com.bank.backend.repository.StatusRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.ContainerWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// import javafx.scene.control.Pagination;

@Service
@Transactional
public class ContainerService {
    
    @Autowired
    ContainerRepository containerRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    FundRepository fundRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    // TO ENTITY
    private Container toEntity(ContainerWrapper wrapper){
        Container entity = new Container();
        if(wrapper.getContainerId() != null){
            Optional<Container> container = containerRepository.findById(wrapper.getContainerId());
            if(!container.isPresent())
                throw new BusinessException("Container not found: " + wrapper.getContainerId() + '.');
            entity = container.get();
        }
        // Optional<University> optionalUniv = universityRepository.findById(wrapper.getUniversityId());
        // List<University> university = optionalUniv.isPresent() ? (List<University>) optionalUniv.get() : null;
        // entity.setUniversity(university);
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
        Status status = statusRepository.getById(wrapper.getStatusId());
        entity.setStatus(status);
        User user = userRepository.getById(wrapper.getUserId());
        entity.setUser(user);
        return entity;
    }

    // TO WRAPPER
    private ContainerWrapper toWrapper(Container entity) {
        ContainerWrapper wrapper = new ContainerWrapper();
        wrapper.setContainerId(entity.getContainerId());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setUniversityName(entity.getUniversity() != null ? entity.getUniversity().getUniversityName() : null);
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setAccountNumber(entity.getAccountNumber());
        wrapper.setMutationId(entity.getMutationId());
        wrapper.setTransactionDate(entity.getTransactionDate());
        wrapper.setValue(entity.getValue());
        wrapper.setPurchaseId(entity.getPurchase() != null ? entity.getPurchase().getPurchaseId() : null);
        wrapper.setPurchaseName(entity.getPurchase() != null ? entity.getPurchase().getPurchaseName() : null);
        wrapper.setAccountTypeId(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeId() : null);
        wrapper.setAccountTypeName(entity.getAccountType() != null ? entity.getAccountType().getAccountTypeName() : null);
        wrapper.setFundId(entity.getFund() != null ? entity.getFund().getFundId() : null);
        wrapper.setFundName(entity.getFund() != null ? entity.getFund().getFundName() : null);
        wrapper.setDescription(entity.getDescription());
        wrapper.setStatusId(entity.getStatus() != null ? entity.getStatus().getStatusId() : null);
        wrapper.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        wrapper.setUsername(entity.getUser() != null ? entity.getUser().getUsername() : null);
        return wrapper;
    }

    // TO WRAPPER LIST
    private List<ContainerWrapper> toWrapperList(List<Container> entityList) {
        List<ContainerWrapper> wrapperList = new ArrayList<ContainerWrapper>();
        for (Container entity : entityList) {
            ContainerWrapper wrapper = toWrapper(entity);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    // TO PAGINATION LIST
    private PaginationList<ContainerWrapper,Container> toPaginationList(Page<Container> entityPage){
        List<Container> entityList = entityPage.getContent();
        List<ContainerWrapper> wrapperList = toWrapperList(entityList);
        PaginationList<ContainerWrapper,Container> paginationList = new PaginationList<>(wrapperList, entityPage);
        return paginationList;
    }

    // FIND ALL
    public List<ContainerWrapper> findAll() {
        List<Container> containerList = containerRepository.findAll();
        return toWrapperList(containerList);
    }

    // FIND ALL WITH PAGINATION
    public PaginationList<ContainerWrapper, Container> findAllWithPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(containerRepository.findAll(paging));
    }

    // FIND ALL BY STATUS
    public PaginationList<ContainerWrapper, Container> findAllByStatus(String status, int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return toPaginationList(containerRepository.findAllByStatus(status, paging));
    }

    // FIND BY STATUS METHOD QUERY
    public PaginationList<ContainerWrapper, Container> findByRequestStatus(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Status status = statusRepository.getById(1L);
        return toPaginationList(containerRepository.findByStatus(status, paging));
    }

    // FIND ALL CATEGORIES
    public PaginationList<ContainerWrapper, Container> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        return toPaginationList(containerRepository.findByAllCategories(all, paging));
    }

    // FIND ALL CATEGORIES BY STATUS
    // public PaginationList<ContainerWrapper, Container> findByStatusAllCategories(String all, int page, int size){
    //     Pageable paging = PageRequest.of(page, size);
    //     return toPaginationList(containerRepository.findByStatusAllCategories(all, paging));
    // }

    // GET BY ID
    public ContainerWrapper findByContainerId(Long id){
        return toWrapper(containerRepository.findById(id).get());
    }
    
    // SAVE
    public ContainerWrapper save(ContainerWrapper wrapper){
        return toWrapper(containerRepository.save(toEntity(wrapper)));
    }

    // DELETE
    public void delete(Long containerId){
		containerRepository.deleteById(containerId);
    }

}
