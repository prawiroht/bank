package com.bank.backend.controller;

import com.bank.backend.service.UserService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
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
    @GetMapping(path = "/findById")
    public DataResponse<UserWrapper> findById(@RequestParam("id")Long id){
        UserWrapper hasil = userService.getById(id);
        if(hasil == null)
            return new DataResponse<UserWrapper>(false,"Data tidak ditemukan");
        return new DataResponse<UserWrapper>(hasil);
    }
    // post&put
    @PostMapping(path = "/post")
    public DataResponse<UserWrapper> post(@RequestBody UserWrapper wrapper){
        return new DataResponse<UserWrapper>(userService.save(wrapper));
    }
    @PutMapping(path = "/put")
    public DataResponse<UserWrapper> put(@RequestBody UserWrapper wrapper){
        return new DataResponse<UserWrapper>(userService.save(wrapper));
    }
    // delete
    @DeleteMapping(path = "/delete")
    public DataResponse<UserWrapper> put(@RequestParam("id") Long id){
        userService.delete(id);
        return new DataResponse<UserWrapper>(true, "Delete Sukses");
    }

}