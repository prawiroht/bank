package com.bank.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.Receiving;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.ReceivingRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReceivingService {

    @Autowired
    ReceivingRepository receivingRepository;
    
    //get
    public List<Receiving> findAll() {
    return receivingRepository.findAll();
    }

    public Receiving findByReceivingId(Long id){
        return receivingRepository.findById(id).get();
    }

    public PaginationList<Receiving, Receiving> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Receiving> receivingPage = receivingRepository.findAll(paging);
        List<Receiving> receivingList = receivingPage.getContent();
        return new PaginationList<Receiving, Receiving>(receivingList, receivingPage);
    }

    public Receiving findById(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID");
        Optional<Receiving> receiving = receivingRepository.findById(id);
        if (!receiving.isPresent())
            throw new BusinessException("ID "+ id +" is not found.");
        return receiving.get();
    }

    public PaginationList<Receiving, Receiving> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<Receiving> receivingPage = receivingRepository.findByAllCategories(all, paging);
        List<Receiving> receivingList = receivingPage.getContent();
        return new PaginationList<Receiving, Receiving>(receivingList, receivingPage);
    }

    //post and put
    public Receiving save(Receiving receiving) {
        if (receiving.getReceivingId() != null) {
            Receiving existedReceiving = receivingRepository.getById(receiving.getReceivingId());
            existedReceiving.setReceivingName(receiving.getReceivingName());
            return receivingRepository.save(existedReceiving);
        } else {
            return receivingRepository.save(receiving);
        }
    }

    //delete
    public void delete(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID.");
        Optional<Receiving> accountType = receivingRepository.findById(id);
        if (!accountType.isPresent())
            throw new BusinessException("Account Type ID "+ id +" is not found.");
        receivingRepository.deleteById(id);
    }
}
