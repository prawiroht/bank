package com.bank.backend.controller;

import com.bank.backend.entity.Mutation;
import com.bank.backend.service.MutationService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping (path = "/mutation")
public class MutationController {
    @Autowired
    MutationService mutationService;

    @GetMapping (path = "/findAll")
    public DataResponseList<Mutation> findAll(){
        return new DataResponseList<Mutation>(mutationService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Mutation, Mutation> findAllPagination(int page, int size){
        return new DataResponsePagination<Mutation, Mutation>(mutationService.findAllPagination(page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<Mutation> post(@RequestBody Mutation mutation){
        return new DataResponse<Mutation>(mutationService.save(mutation));
    }

    @PutMapping (path = "/put")
    public DataResponse<Mutation> put(@RequestBody Mutation mutation){
        try {
            return new DataResponse<Mutation>(mutationService.save(mutation));
        } catch (Exception e) {
            return new DataResponse<Mutation>(false, "Mutation with that ID is not found");
        }
    }

    @DeleteMapping (path = "/delete")
    public DataResponse<Mutation> delete(@RequestParam("id") String id){
        try {
            mutationService.delete(id);
            return new DataResponse<Mutation>(true, "Data is deleted");
        } catch (Exception e){
            return new DataResponse<Mutation>(false, "Data with that ID is not found");
        }
    }
}
