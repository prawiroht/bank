package com.bank.backend.controller;

import com.bank.backend.entity.MutationRight;
import com.bank.backend.service.MutationRightService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.MutationRightWrapper;

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
@RequestMapping (path = "/mutationRight")
public class MutationRightController {
    @Autowired
    MutationRightService mutationRightService;

    @GetMapping (path = "/findAll")
    public DataResponseList<MutationRightWrapper> findAll(){
        return new DataResponseList<MutationRightWrapper>(mutationRightService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<MutationRightWrapper, MutationRight> findAllPagination(int page, int size){
        return new DataResponsePagination<MutationRightWrapper, MutationRight>(mutationRightService.findAllPagination(page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<MutationRightWrapper> post(@RequestBody MutationRightWrapper wrapper){
        try {
            return new DataResponse<MutationRightWrapper>(mutationRightService.save(wrapper));
        } catch (Exception e){
            String errorMessage;
            if (e.getMessage().contains("Mutation")){
                errorMessage = "Mutation with ID " + wrapper.getMutationId() + " is not found.";
            } else if (e.getMessage().contains("Bank")){
                errorMessage = "Bank with ID " + wrapper.getBankId() + " is not found.";
            } else {
                errorMessage = e.getMessage();
            }
            return new DataResponse<MutationRightWrapper>(false, errorMessage);
        }
    }

    @PutMapping (path = "/put")
    public DataResponse<MutationRightWrapper> put(@RequestBody MutationRightWrapper wrapper){
        try {
            return new DataResponse<MutationRightWrapper>(mutationRightService.save(wrapper));
        } catch (Exception e){
            String errorMessage;
            if (e.getMessage().contains("MutationRight")){
                errorMessage = "Mutation Right with ID " + wrapper.getMutationRightId() + " is not found.";
            } else if (e.getMessage().contains("Mutation")){
                errorMessage = "Mutation with ID " + wrapper.getMutationId() + " is not found.";
            } else if (e.getMessage().contains("Bank")){
                errorMessage = "Bank with ID " + wrapper.getBankId() + " is not found.";
            } else {
                errorMessage = e.getMessage();
            }
            return new DataResponse<MutationRightWrapper>(false, errorMessage);
        }
    }

    @DeleteMapping (path = "/delete")
    public DataResponse<MutationRightWrapper> delete(@RequestParam("id") Long id){
        try {
            mutationRightService.delete(id);
            return new DataResponse<MutationRightWrapper>(true, "Data is deleted");
        } catch (Exception e){
            return new DataResponse<MutationRightWrapper>(false, "Mutation Right with  that ID is not found");
        }
    }
}
