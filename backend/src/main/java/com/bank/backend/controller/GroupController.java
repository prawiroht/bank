package com.bank.backend.controller;

import com.bank.backend.entity.Group;
import com.bank.backend.service.GroupService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.GroupWrapper;

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
@RequestMapping(path = "/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping(path = "/findAll")
    public DataResponseList<GroupWrapper> findAll(){
        return new DataResponseList<GroupWrapper>(groupService.findAll());
    }
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<GroupWrapper,Group> findAllPagination(@RequestParam int page,@RequestParam int size){
        return new DataResponsePagination<GroupWrapper,Group>(groupService.findAllPagination(page, size));
    }
    @PostMapping(path = "/post")
    public DataResponse<GroupWrapper> post(@RequestBody GroupWrapper wrapper){
        return new DataResponse<GroupWrapper>(groupService.save(wrapper));
    }
    @PutMapping(path = "/update")
    public DataResponse<GroupWrapper> update(@RequestBody GroupWrapper wrapper){
        return new DataResponse<GroupWrapper>(groupService.save(wrapper));
    }
    @DeleteMapping(path = "/delete")
    public DataResponse<GroupWrapper> delete(@RequestParam Long id){
        groupService.delete(id);
        return new DataResponse<GroupWrapper>(true, "Data berhasil dihapus");
    }
}
