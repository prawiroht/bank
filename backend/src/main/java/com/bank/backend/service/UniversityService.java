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

    public List<University> findAll(){
        return universityRepository.findAll();
    }

    public PaginationList<University, University> findAllPagination(int page, int size){
        Pageable paging = PageRequest.of(page, size);
        Page<University> bankPage = universityRepository.findAll(paging);
        List<University> universityList = bankPage.getContent();
        return new PaginationList<University, University>(universityList, bankPage);
    }

    public University getByLocationId(Long universityId) {
		if (universityId == null)
			throw new BusinessException("ID cannot be null.");
		Optional<University> university = universityRepository.findById(universityId);
		if (!university.isPresent())
			throw new BusinessException("Location with id " + universityId + " is not found.");
		return university.get();
	}

    public University save(University university) {
		University universitySave = universityRepository.save(university);
		return universitySave;
	}

    public void delete(Long universityId) {
		if (universityId == null)
			throw new BusinessException("ID cannot be null.");
		Optional<University> university = universityRepository.findById(universityId);
		if (!university.isPresent())
			throw new BusinessException("Location with ID " + universityId + " is not found");
		universityRepository.deleteById(universityId);
	}

}
