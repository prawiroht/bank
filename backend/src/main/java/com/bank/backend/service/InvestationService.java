package com.bank.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.bank.backend.entity.Investation;
import com.bank.backend.entity.Status;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.BankRepository;
import com.bank.backend.repository.InvestationRepository;
import com.bank.backend.repository.StatusRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.repository.UserRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.InvestationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvestationService {
    @Autowired
    InvestationRepository investationRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;

    // util
    private Investation toEntity(InvestationWrapper wrapper) {
        Investation entity = new Investation();
        if (wrapper.getInvestationId() != null) {
            entity = investationRepository.getById(wrapper.getInvestationId());
        }
        entity.setUniversity(universityRepository.getById(wrapper.getUniversityId()));
        entity.setInvestationName(wrapper.getInvestationName());
        entity.setInitialNAB(wrapper.getInitialNAB());
        entity.setInitialUnit(wrapper.getInitialUnit());
        entity.setInitialValue(wrapper.getInitialValue());
        entity.setMarketNAB(wrapper.getMarketNAB());
        entity.setMarketUnit(wrapper.getMarketUnit());
        entity.setMarketValue(wrapper.getMarketValue());
        entity.setStartDate(wrapper.getStartDate());
        entity.setBank(bankRepository.getById(wrapper.getBankId()));
        entity.setStatus(statusRepository.getById(wrapper.getStatusId()));
        entity.setUser(userRepository.getById(wrapper.getUserId()));
        return entity;
    }

    private InvestationWrapper toWrapper(Investation entity) {
        InvestationWrapper wrapper = new InvestationWrapper();
        wrapper.setInvestationId(entity.getInvestationId());
        wrapper.setInvestationName(entity.getInvestationName());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setInitialNAB(entity.getInitialNAB());
        wrapper.setInitialUnit(entity.getInitialUnit());
        wrapper.setInitialValue(entity.getInitialValue());
        wrapper.setMarketNAB(entity.getMarketNAB());
        wrapper.setMarketUnit(entity.getMarketUnit());
        wrapper.setMarketValue(entity.getMarketValue());
        wrapper.setStartDate(entity.getStartDate());
        wrapper.setBankId(entity.getBank() != null ? entity.getBank().getBankId() : null);
        wrapper.setBankName(entity.getBank() != null ? entity.getBank().getBankName() : null);
        wrapper.setStatusId(entity.getStatus() != null ? entity.getStatus().getStatusId() : null);
        wrapper.setStatusName(entity.getStatus() != null ? entity.getStatus().getStatusName() : null);
        wrapper.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        wrapper.setUserName(entity.getUser() != null ? entity.getUser().getUsername() : null);
        return wrapper;
    }

    private List<InvestationWrapper> toWrapperList(List<Investation> entityList) {
        List<InvestationWrapper> wrapperList = new ArrayList<InvestationWrapper>();
        for (Investation entity : entityList) {
            InvestationWrapper wrapper = toWrapper(entity);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    // Retrieve single data
    public InvestationWrapper getInvestationById(Long InvestationId) {
        return toWrapper(investationRepository.findById(InvestationId).get());
    }

    // Retrieve all data
    public List<InvestationWrapper> findAll() {
        return toWrapperList(investationRepository.findAll());
    }

    // Retrieve all data with pagination
    public PaginationList<InvestationWrapper, Investation> findAllWithPaginationList(int page, int size) {
        Page<Investation> investationPage = investationRepository.findAll(PageRequest.of(page, size));
        List<Investation> investationList = investationPage.getContent();
        List<InvestationWrapper> investationWrappers = toWrapperList(investationList);
        return new PaginationList<InvestationWrapper, Investation>(investationWrappers, investationPage);
    }

    // Retrive all data with param and pagination
    public PaginationList<InvestationWrapper, Investation> findAllCategoriesWithPagination(String all, int page,
            int size) {
        Page<Investation> investationPage = investationRepository.getByAllCategories(all, PageRequest.of(page, size));
        List<Investation> iList = investationPage.getContent();
        List<InvestationWrapper> investationWrappers = toWrapperList(iList);
        return new PaginationList<InvestationWrapper, Investation>(investationWrappers, investationPage);
    }

    public Long sumNominalWithStatusApprove() {
        return investationRepository.sumNominalWithStatusApprove();
    }

    public Long sumNominalWithParam(Date startDate, Date endDate, Long bankId) {
        return investationRepository.sumNominalWithStatusApproveAndParam(startDate,
                endDate, bankId);
    }

    // Create and update data
    public InvestationWrapper save(InvestationWrapper wrapper) {
        return toWrapper(investationRepository.save(toEntity(wrapper)));
    }

    // Delete data
    public void delete(Long id) {
        if (id == null)
            throw new BusinessException("Id cannot be null");
        investationRepository.deleteById(id);
    }

    // Find by request status
    public PaginationList<InvestationWrapper, Investation> findByRequestStatus(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Status status = statusRepository.getById(1L);
        Page<Investation> investationPage = investationRepository.findByStatus(status, paging);
        List<Investation> investationList = investationPage.getContent();
        List<InvestationWrapper> investationWrappers = toWrapperList(investationList);
        return new PaginationList<InvestationWrapper, Investation>(investationWrappers, investationPage);
    }
}
