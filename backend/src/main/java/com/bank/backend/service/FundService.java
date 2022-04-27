package com.bank.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.Fund;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.FundRepository;
import com.bank.backend.util.PaginationList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FundService {
    
    @Autowired
    FundRepository fundRepository;

    public List<Fund> findAll() {
        return fundRepository.findAll();
    }

    public PaginationList<Fund, Fund> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Fund> fundPage = fundRepository.findAll(paging);
        List<Fund> fundList = fundPage.getContent();
        return new PaginationList<Fund, Fund>(fundList, fundPage);
    }

    public Fund findById(Long id){
        if (id == null)
            throw new BusinessException("Please insert ID");
        Optional<Fund> fund = fundRepository.findById(id);
        if (!fund.isPresent())
            throw new BusinessException("ID "+ id +" is not found.");
        return fund.get();
    }

    public PaginationList<Fund, Fund> findAllCategories(String all, int page, int size){
        Pageable paging  = PageRequest.of(page, size);
        Page<Fund> fundPage = fundRepository.findByAllCategories(all, paging);
        List<Fund> fundList = fundPage.getContent();
        return new PaginationList<Fund, Fund>(fundList, fundPage);
    }

    //post and put
    public Fund save(Fund fund) {
		if (fund.getFundId() != null) {
			Fund existedFund = fundRepository.getById(fund.getFundId());
			existedFund.setAlias(fund.getAlias());
            existedFund.setFundName(fund.getFundName());
			return fundRepository.save(existedFund);
		} else {
			return fundRepository.save(fund);
		}
	}

    //delete
    public void delete(Long id){
        if (id == null)
	        throw new BusinessException("Please insert ID.");
		Optional<Fund> entity = fundRepository.findById(id);
		if (!entity.isPresent())
			throw new BusinessException("Bank "+ id +" is not found.");
		fundRepository.deleteById(id);
    }
}
