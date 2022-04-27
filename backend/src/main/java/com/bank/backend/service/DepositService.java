package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Bank;
import com.bank.backend.entity.Deposit;
import com.bank.backend.entity.Period;
import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.DepositRepository;
import com.bank.backend.repository.PeriodRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.DepositWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    // util
    private Deposit toEntity(DepositWrapper wrapper) {
        Deposit entity = new Deposit();
        if (wrapper.getDepositId() != null) {
            Optional<Deposit> deposit = depositRepository.findById(wrapper.getDepositId());
            if (!deposit.isPresent())
                throw new BusinessException("Current Account not found: " + wrapper.getDepositId() + '.');
            entity = deposit.get();
        }
        Optional<University> optionalUniv = universityRepository.findById(wrapper.getUniversityId());
        University university = optionalUniv.orElse(null);
        entity.setUniversity(university);
        entity.setDepositName(wrapper.getDepositName());
        Optional<Bank> optionalBank = bankRepository.findById(wrapper.getBankId());
        Bank bank = optionalBank.orElse(null);
        entity.setBank(bank);
        entity.setAccountNumber(wrapper.getAccountNumber());
        Optional<Period> optionalPeriod = periodRepository.findById(wrapper.getPeriodId());
        Period period = optionalPeriod.orElse(null);
        entity.setPeriod(period);
        entity.setNominal(wrapper.getNominal());
        entity.setInterest(wrapper.getInterest());
        entity.setEarningInterest(wrapper.getEarningInterest());
        entity.setStartDate(wrapper.getStartDate());
        entity.setDueDate(wrapper.getDueDate());
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
        Optional<Deposit> entity = depositRepository.findById(id);
        if (!entity.isPresent())
            throw new BusinessException("Cannot found Deposit with id : " + id + ".");
        depositRepository.deleteById(id);
    }
}
