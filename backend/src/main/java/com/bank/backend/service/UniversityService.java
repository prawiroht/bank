package com.bank.backend.service;

import java.util.List;
// import java.util.Optional;

import javax.transaction.Transactional;

import com.bank.backend.entity.University;
// import com.bank.backend.exception.BusinessException;
import com.bank.backend.repository.UniversityRepository;
import com.bank.backend.util.PaginationList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
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
		University university = universityRepository.findById(universityId).get();
		return university;
	}

	// ADD (POST) & UPDATE (PUT)
    public University save(University university) {
		University universitySave = universityRepository.save(university);
		return universitySave;
	}

	// DELETE
    public void delete(Long universityId) {
		universityRepository.deleteById(universityId);
	}

	// CSV
	public List<University> listAll() {
        return universityRepository.findAll();
    }

}
