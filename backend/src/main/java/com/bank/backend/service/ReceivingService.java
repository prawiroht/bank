package com.bank.backend.service;

import javax.transaction.Transactional;

import com.bank.backend.repository.ReceivingRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReceivingService {

    @Autowired
    ReceivingRepository receivingRepository;
    

}
