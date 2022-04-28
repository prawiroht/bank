package com.bank.backend.controller;

import com.bank.backend.entity.University;
import com.bank.backend.service.UniversityService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping (path = "/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    // FIND ALL
    @GetMapping (path = "/findAll")
    public DataResponseList<University> findAll(){
        return new DataResponseList<University>(universityService.findAll());
    }

    // FIND ALL WITH PAGINATION
    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<University, University> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<University, University>(universityService.findAllPagination(page, size));
    };

    // FIND BY UNIVERSITY NAME
    @GetMapping(path = "/findByUniversityName")
    public DataResponsePagination<University, University> findByUniversityName(@RequestParam("search") String all, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<University, University>(universityService.findByUniversityName(all, page, size));
    }

    // GET BY ID
    @GetMapping(path = "/getById")
	public DataResponse<University> getByUniversityId(Long universityId) {
        try {
            return new DataResponse<University>(universityService.getByUniversityId(universityId));
        } catch (Exception e) {
            return new DataResponse<University>(false, "Universitas dengan id " + universityId + " tidak ditemukan");
        }
		
	}

    // POST / ADD
	@PostMapping(path="/post")
	public DataResponse<University> save(@RequestBody University university) {
		return new DataResponse<University>(universityService.save(university));
	}
	
	// PUT / EDIT
	@PutMapping(path="/put")
	public DataResponse<University> update(@RequestBody University university) {
        try {
            return new DataResponse<University>(universityService.save(university));
        } catch (Exception e) {
            return new DataResponse<University>(false, "Universitas dengan id " + university.getUniversityId() + " tidak ditemukan");
        }
	}	

    // DELETE
    @DeleteMapping(path = "/{id}")
	public DataResponse<University> delete(@PathVariable("id") Long universityId) {
        try {
            universityService.delete(universityId);
            return new DataResponse<University>(true, "Delete sukses");
        } catch (Exception e) {
            return new DataResponse<University>(false, "Universitas dengan id " + universityId + " tidak ditemukan");
        }
		
	}

}
