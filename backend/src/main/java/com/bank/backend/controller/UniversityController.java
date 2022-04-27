package com.bank.backend.controller;

import com.bank.backend.entity.University;
import com.bank.backend.service.UniversityService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping (path = "/findAll")
    public DataResponseList<University> findAll(){
        return new DataResponseList<University>(universityService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<University, University> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<University, University>(universityService.findAllPagination(page, size));
    };

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
	public DataResponse<University> getByLocationId(@RequestParam("id") Long universityId) {
		return new DataResponse<University>(universityService.getByLocationId(universityId));
	}

    // POST / ADD
	@PostMapping(path="/post")
	public DataResponse<University> save(@RequestBody University university) {
		return new DataResponse<University>(universityService.save(university));
	}
	
	// PUT / EDIT
	@PutMapping(path="/put")
	public DataResponse<University> update(@RequestBody University university) {
		return new DataResponse<University>(universityService.save(university));
	}	

    // DELETE
    @DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long universityId) {
		universityService.delete(universityId);
	}

}
