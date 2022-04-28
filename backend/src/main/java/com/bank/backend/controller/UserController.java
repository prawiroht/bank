package com.bank.backend.controller;

import com.bank.backend.entity.User;
import com.bank.backend.service.UserService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.UserWrapper;

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
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;
    // get
    @GetMapping(path = "/findAll")
    public DataResponseList<UserWrapper> findAll(){
        return new DataResponseList<UserWrapper>(userService.findAll());
    }
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<UserWrapper,User> findAllPagination(@RequestParam("page")int page,@RequestParam("size")int size){
        return new DataResponsePagination<UserWrapper,User>(userService.findAll(page,size));
    }
    @GetMapping(path = "/findById")
    public DataResponse<UserWrapper> findById(@RequestParam("id")Long id){
        try {
            UserWrapper hasil = userService.getById(id);
            return new DataResponse<UserWrapper>(hasil);      
        } catch (Exception e) {
            return new DataResponse<UserWrapper>(false, "User tidak ditemukan: "+ id);
        }
        
    }
    @GetMapping(path = "/findByUsername")
    public DataResponse<UserWrapper> findByUsername(@RequestParam String username){
        try {
            UserWrapper hasil = userService.getByUsername(username);
            return new DataResponse<UserWrapper>(hasil);    
        } catch (Exception e) {
            return new DataResponse<UserWrapper>(false, "User tidak ditemukan: "+ username);
        }
        
    }
    @GetMapping(path = "/findByEmail")
    public DataResponse<UserWrapper> findByEmail(@RequestParam String email){
        try{
            UserWrapper hasil = userService.getByEmail(email);
            return new DataResponse<UserWrapper>(hasil);
        }catch(Exception e){
            return new DataResponse<UserWrapper>(false, "User tidak ditemukan: "+ email);
        }
        
    }
    // post&put
    @PostMapping(path = "/post")
    public DataResponse<UserWrapper> post(@RequestBody UserWrapper wrapper){
        try {
            return new DataResponse<UserWrapper>(userService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("University"))
                errorMessage = "Universitas tidak ditemukan: "+ wrapper.getUniversityId();
            else
                errorMessage = e.getMessage();
            return new DataResponse<UserWrapper>(false, errorMessage);
        }
    }
    @PutMapping(path = "/update")
    public DataResponse<UserWrapper> update(@RequestBody UserWrapper wrapper){
        try {
            return new DataResponse<UserWrapper>(userService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("User"))
                errorMessage = "User tidak ditemukan: "+ wrapper.getUserId();
            else if(e.getMessage().contains("University"))
                errorMessage = "Universitas tidak ditemukan: "+ wrapper.getUniversityId();
            else
                errorMessage = e.getMessage();
            return new DataResponse<UserWrapper>(false, errorMessage);
        }
    }
    // delete
    @DeleteMapping(path = "/delete")
    public DataResponse<UserWrapper> delete(@RequestParam("id") Long id){
        try {
            userService.delete(id);
            return new DataResponse<UserWrapper>(true, "Delete Sukses");    
        } catch (Exception e) {
            return new DataResponse<UserWrapper>(false, "User tidak ditemukan: "+ id);
        }
    }

}
