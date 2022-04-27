package com.bank.backend.controller;

import com.bank.backend.entity.AccessRight;
import com.bank.backend.service.AccessRightService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.AccessRightWrapper;

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
@RequestMapping(path = "/access")
public class AccessRightController {
    @Autowired
    AccessRightService accessRightService;

    @GetMapping(path = "/findAll")
    public DataResponseList<AccessRightWrapper> findAll(){
        return new DataResponseList<AccessRightWrapper>(accessRightService.findAll());
    }
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<AccessRightWrapper,AccessRight> findAllPagination(@RequestParam int page,@RequestParam int size){
        return new DataResponsePagination<>(accessRightService.findAllPagination(page, size));
    }
    @PostMapping(path = "/post")
    public DataResponse<AccessRightWrapper> post(@RequestBody AccessRightWrapper wrapper){
        return new DataResponse<AccessRightWrapper>(accessRightService.save(wrapper));
    }
    @PutMapping(path = "/update")
    public DataResponse<AccessRightWrapper> update(@RequestBody AccessRightWrapper wrapper){
        return new DataResponse<AccessRightWrapper>(accessRightService.save(wrapper));
    }
    @PutMapping(path = "/updateByUserIdAndGroupId")
    public DataResponse<AccessRightWrapper> updateByUserIdAndGroupId(@RequestParam Long userId,@RequestParam Long groupId, @RequestParam Character isActive){
        return new DataResponse<AccessRightWrapper>(accessRightService.saveByUserIdAndGroupId(userId, groupId, isActive));
    }
    @DeleteMapping(path = "/delete")
    public DataResponse<AccessRightWrapper> delete(@RequestParam Long id){
        accessRightService.delete(id);
        return new DataResponse<AccessRightWrapper>(true,"Data berhasil dihapus");
    }
}
