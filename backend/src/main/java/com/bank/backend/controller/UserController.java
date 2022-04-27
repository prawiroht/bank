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
        UserWrapper hasil = userService.getById(id);
        if(hasil == null)
            return new DataResponse<UserWrapper>(false,"Data tidak ditemukan");
        return new DataResponse<UserWrapper>(hasil);
    }
    @GetMapping(path = "/findByUsername")
    public DataResponse<UserWrapper> findByUsername(@RequestParam String username){
        UserWrapper hasil = userService.getByUsername(username);
        if(hasil == null)
            return new DataResponse<UserWrapper>(false,"Data tidak ditemukan");
        return new DataResponse<UserWrapper>(hasil);
    }
    @GetMapping(path = "/findByEmail")
    public DataResponse<UserWrapper> findByEmail(@RequestParam String email){
        UserWrapper hasil = userService.getByEmail(email);
        if(hasil == null)
            return new DataResponse<UserWrapper>(false,"Data tidak ditemukan");
        return new DataResponse<UserWrapper>(hasil);
    }
    // post&put
    @PostMapping(path = "/post")
    public DataResponse<UserWrapper> post(@RequestBody UserWrapper wrapper){
        return new DataResponse<UserWrapper>(userService.save(wrapper));
    }
    @PutMapping(path = "/update")
    public DataResponse<UserWrapper> update(@RequestBody UserWrapper wrapper){
        return new DataResponse<UserWrapper>(userService.save(wrapper));
    }
    // delete
    @DeleteMapping(path = "/delete")
    public DataResponse<UserWrapper> put(@RequestParam("id") Long id){
        userService.delete(id);
        return new DataResponse<UserWrapper>(true, "Delete Sukses");
    }

}
