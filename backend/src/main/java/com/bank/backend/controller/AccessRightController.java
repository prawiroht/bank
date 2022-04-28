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
        try {
            return new DataResponse<AccessRightWrapper>(accessRightService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("Group")){
                errorMessage = "Group tidak ditemukan: "+ wrapper.getGroupId();
            }
            else if(e.getMessage().contains("User")){
                errorMessage = "User tidak ditemukan: "+ wrapper.getUserId();
            }
            else{
                errorMessage = e.getMessage();
            }
            return new DataResponse<AccessRightWrapper>(false, errorMessage);
        }
    }
    @PutMapping(path = "/update")
    public DataResponse<AccessRightWrapper> update(@RequestBody AccessRightWrapper wrapper){
        try {
            return new DataResponse<AccessRightWrapper>(accessRightService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if (e.getMessage().contains("AccessRight"))
                errorMessage = "Hak akses tidak ditemukan: "+ wrapper.getAccessRightId();
            else if(e.getMessage().contains("Group")){
                errorMessage = "Group tidak ditemukan: "+ wrapper.getGroupId();
            }
            else if(e.getMessage().contains("User")){
                errorMessage = "User tidak ditemukan: "+ wrapper.getUserId();
            }
            else{
                errorMessage = e.getMessage();
            }
            return new DataResponse<AccessRightWrapper>(false, errorMessage);
        }
        
    }
    @PutMapping(path = "/updateByUserIdAndGroupId")
    public DataResponse<AccessRightWrapper> updateByUserIdAndGroupId(@RequestParam Long userId,@RequestParam Long groupId, @RequestParam Character isActive){
        try {
            return new DataResponse<AccessRightWrapper>(accessRightService.saveByUserIdAndGroupId(userId, groupId, isActive));    
        } catch (Exception e) {
            return new DataResponse<AccessRightWrapper>(false, "Hak akses tidak ditemukan: userId="+ userId +", groupId="+groupId);
        }
        
    }
    @DeleteMapping(path = "/delete")
    public DataResponse<AccessRightWrapper> delete(@RequestParam Long id){
        try {
            accessRightService.delete(id);
            return new DataResponse<AccessRightWrapper>(true,"Data berhasil dihapus");    
        } catch (Exception e) {
            return new DataResponse<AccessRightWrapper>(false, "Hak akses tidak ditemukan: "+id);
        }
    }
}
