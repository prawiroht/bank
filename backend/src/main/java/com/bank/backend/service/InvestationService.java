package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Investation;
import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.InvestationRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;
import com.bank.backend.wrapper.InvestationWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvestationService {
    @Autowired
    InvestationRepository investationRepository;
    @Autowired
    UniversityRepository universityRepository;

    // util
    private Investation toEntity(InvestationWrapper wrapper) {
        Investation entity = new Investation();
        if (wrapper.getInvestationId() != null) {
            Optional<Investation> Investation = investationRepository.findById(wrapper.getInvestationId());
            if (!Investation.isPresent())
                throw new BusinessException("Current Account not found: " + wrapper.getInvestationId() + '.');
            entity = Investation.get();
        }
        Optional<University> optionalUniv = universityRepository.findById(wrapper.getUniversityId());
        University university = optionalUniv.orElse(null);
        entity.setUniversity(university);
        entity.setInvestationName(wrapper.getInvestationName());
        entity.setInitialSaving(wrapper.getInitialSaving());
        entity.setInitialUnit(wrapper.getInitialUnit());
        entity.setInitialValue(wrapper.getInitialValue());
        entity.setMarketSaving(wrapper.getMarketSaving());
        entity.setMarketUnit(wrapper.getMarketUnit());
        entity.setMarketValue(wrapper.getMarketValue());
        entity.setStartDate(wrapper.getStartDate());
        return entity;
    }

    private InvestationWrapper toWrapper(Investation entity) {
        InvestationWrapper wrapper = new InvestationWrapper();
        wrapper.setInvestationId(entity.getInvestationId());
        wrapper.setInvestationName(entity.getInvestationName());
        wrapper.setUniversityId(entity.getUniversity() != null ? entity.getUniversity().getUniversityId() : null);
        wrapper.setInitialSaving(entity.getInitialSaving());
        wrapper.setInitialUnit(entity.getInitialUnit());
        wrapper.setInitialValue(entity.getInitialValue());
        wrapper.setMarketSaving(entity.getMarketSaving());
        wrapper.setMarketUnit(entity.getMarketUnit());
        wrapper.setMarketValue(entity.getMarketValue());
        wrapper.setStartDate(entity.getStartDate());
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
        Page<Investation> InvestationPage = investationRepository.findAll(PageRequest.of(page, size));
        List<Investation> InvestationList = InvestationPage.getContent();
        List<InvestationWrapper> InvestationWrappers = toWrapperList(InvestationList);
        return new PaginationList<>(InvestationWrappers, InvestationPage);
    }

    // Create and update data
    public InvestationWrapper save(InvestationWrapper wrapper) {
        return toWrapper(investationRepository.save(toEntity(wrapper)));
    }

    // Delete data
    public void delete(Long id) {
        if (id == null)
            throw new BusinessException("Id cannot be null");
        Optional<Investation> entity = investationRepository.findById(id);
        if (!entity.isPresent())
            throw new BusinessException("Cannot found Investation with id : " + id + ".");
        investationRepository.deleteById(id);
    }
}
