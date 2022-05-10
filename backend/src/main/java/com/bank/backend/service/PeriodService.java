package com.bank.backend.service;

import java.util.List;

import com.bank.backend.entity.Period;
import com.bank.backend.repository.PeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeriodService {
    @Autowired
    PeriodRepository periodRepository;

    // FIND ALL
    public List<Period> findAll() {
        return periodRepository.findAll();
    }
}
