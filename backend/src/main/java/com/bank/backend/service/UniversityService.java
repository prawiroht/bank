package com.bank.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.University;
import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UniversityService {
    
    @Autowired
    UniversityRepository universityRepository;

	// FIND ALL
    public List<University> findAll(){
        return universityRepository.findAll();
    }

	// FIND ALL WITH PAGINATION
    public PaginationList<University, University> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<University> universityPage = universityRepository.findAll(paging);
        List<University> universityList = universityPage.getContent();
        return new PaginationList<University, University>(universityList, universityPage);
    }

	// FIND BY UNIVERSITY NAME
	public PaginationList<University, University> findByUniversityName(String all, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<University> universityPage = universityRepository.findByUniversityNameContainingIgnoreCase(all, paging);
		List<University> universityList = universityPage.getContent();
		return new PaginationList<University, University>(universityList, universityPage);
	}

	// GET BY ID
    public University getByUniversityId(Long universityId) {
		if (universityId == null)
			throw new BusinessException("ID cannot be null.");
		Optional<University> university = universityRepository.findById(universityId);
		if (!university.isPresent())
			throw new BusinessException("University with id " + universityId + " is not found.");
		return university.get();
	}

	// ADD (POST) & UPDATE (PUT)
    public University save(University university) {
		University universitySave = universityRepository.save(university);
		return universitySave;
	}

	// DELETE
    public void delete(Long universityId) {
		if (universityId == null)
			throw new BusinessException("ID cannot be null.");
		Optional<University> university = universityRepository.findById(universityId);
		if (!university.isPresent())
			throw new BusinessException("University with ID " + universityId + " is not found");
		universityRepository.deleteById(universityId);
	}

}
