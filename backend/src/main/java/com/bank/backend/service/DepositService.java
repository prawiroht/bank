package com.bank.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.backend.entity.Deposit;
import com.bank.backend.entity.Status;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.DepositRepository;
import com.bank.backend.repository.PeriodRepository;
import com.bank.backend.repository.StatusRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.DepositWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepositService {
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;

    // util
    private Deposit toEntity(DepositWrapper wrapper) {
        Deposit entity = new Deposit();
        if (wrapper.getDepositId() != null) {
            entity = depositRepository.getById(wrapper.getDepositId());
        }
        entity.setUniversity(universityRepository.getById(wrapper.getUniversityId()));
        entity.setDepositName(wrapper.getDepositName());
        entity.setBank(bankRepository.getById(wrapper.getBankId()));
        entity.setAccountNumber(wrapper.getAccountNumber());
        entity.setPeriod(periodRepository.getById(wrapper.getPeriodId()));
        entity.setNominal(wrapper.getNominal());
        entity.setInterest(wrapper.getInterest());
        entity.setEarningInterest(wrapper.getEarningInterest());
        entity.setStartDate(wrapper.getStartDate());
        entity.setDueDate(wrapper.getDueDate());
        entity.setStatus(statusRepository.getById(wrapper.getStatusId()));
        entity.setUser(userRepository.getById(wrapper.getUserId()));
        return entity;
    }

    private DepositWrapper toWrapper(Deposit entity) {
        DepositWrapper wrapper = new DepositWrapper();
        wrapper.setDepositId(entity.getDepositId());
        wrapper.setDepositName(entity.getDepositName());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setCode(entity.getBank() != null ? entity.getBank().getCode() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setAccountNumber(entity.getAccountNumber());
        wrapper.setPeriodId(entity.getPeriod() != null ? entity.getPeriod().getPeriodId() : null);
        wrapper.setPeriod(entity.getPeriod() != null ? entity.getPeriod().getPeriod() : null);
        wrapper.setNominal(entity.getNominal());
        wrapper.setInterest(entity.getInterest());
        wrapper.setEarningInterest(entity.getEarningInterest());
        wrapper.setStartDate(entity.getStartDate());
        wrapper.setDueDate(entity.getDueDate());
        wrapper.setStatusId(entity.getStatus() != null ? entity.getStatus().getStatusId() : null);
        wrapper.setStatusName(entity.getStatus() != null ? entity.getStatus().getStatusName() : null);
        wrapper.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        wrapper.setUserName(entity.getUser() != null ? entity.getUser().getUsername() : null);
        return wrapper;
    }

    private List<DepositWrapper> toWrapperList(List<Deposit> entityList) {
        List<DepositWrapper> wrapperList = new ArrayList<DepositWrapper>();
        for (Deposit entity : entityList) {
            DepositWrapper wrapper = toWrapper(entity);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    // Retrieve single data
    public DepositWrapper getDepositById(Long depositId) {
        return toWrapper(depositRepository.findById(depositId).get());
    }

    // Retrieve all data
    public List<DepositWrapper> findAll() {
        return toWrapperList(depositRepository.findAll());
    }

    public Long sumNominalWithStatusApprove() {
        return depositRepository.sumNominalWithStatusApprove();
    }

    public Long sumNominalWithParam(Date startDate, Date endDate, Long bankId) {
        return depositRepository.sumNominalWithStatusApproveAndParam(startDate, endDate, bankId);
    }

    // Retrieve all data with pagination
    public PaginationList<DepositWrapper, Deposit> findAllWithPaginationList(int page, int size) {
        Page<Deposit> depositPage = depositRepository.findAll(PageRequest.of(page, size));
        List<Deposit> depositList = depositPage.getContent();
        List<DepositWrapper> depositWrappers = toWrapperList(depositList);
        return new PaginationList<>(depositWrappers, depositPage);
    }

    // Create and update data
    public DepositWrapper save(DepositWrapper wrapper) {
        return toWrapper(depositRepository.save(toEntity(wrapper)));
    }

    // Delete data
    public void delete(Long id) {
        if (id == null)
            throw new BusinessException("Id cannot be null");
        depositRepository.deleteById(id);
    }

    // Retrieve list of data with pagination with param all category
    public PaginationList<DepositWrapper, Deposit> getAllCategories(String all, int page, int size) {
        Page<Deposit> depositPage = depositRepository.getByAllCategories(all, PageRequest.of(page, size));
        List<Deposit> depositList = depositPage.getContent();
        List<DepositWrapper> depositWrappers = toWrapperList(depositList);
        return new PaginationList<DepositWrapper, Deposit>(depositWrappers, depositPage);
    }

    public PaginationList<DepositWrapper, Deposit> findByRequestStatus(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Status status = statusRepository.getById(1L);
        Page<Deposit> depositPage = depositRepository.findByStatus(status, paging);
        List<Deposit> depositList = depositPage.getContent();
        List<DepositWrapper> depositWrappers = toWrapperList(depositList);
        return new PaginationList<DepositWrapper, Deposit>(depositWrappers, depositPage); 
    }
}
