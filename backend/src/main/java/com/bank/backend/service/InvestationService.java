package com.bank.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.backend.entity.Investation;
import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.InvestationRepository;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.wrapper.InvestationWrapper;

import org.springframework.beans.factory.annotation.Autowired;

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

}
