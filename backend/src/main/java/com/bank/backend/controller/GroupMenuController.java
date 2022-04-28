package com.bank.backend.controller;

import com.bank.backend.entity.GroupMenu;
import com.bank.backend.service.GroupMenuService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.GroupMenuWrapper;

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
@RequestMapping(path = "/groupMenu")
public class GroupMenuController {
    @Autowired
    GroupMenuService groupMenuService;

    @GetMapping(path = "/findAll")
    public DataResponseList<GroupMenuWrapper> findAll(){
        return new DataResponseList<GroupMenuWrapper>(groupMenuService.findAll());
    }
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<GroupMenuWrapper,GroupMenu> findAllPagination(@RequestParam int page,@RequestParam int size){
        return new DataResponsePagination<>(groupMenuService.findAllPagination(page, size));
    }
    @PostMapping(path = "/post")
    public DataResponse<GroupMenuWrapper> post(@RequestBody GroupMenuWrapper wrapper){
        try {
            return new DataResponse<GroupMenuWrapper>(groupMenuService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("Group")){
                errorMessage = "Group tidak ditemukan: "+ wrapper.getGroupId();
            }
            else if(e.getMessage().contains("Menu")){
                errorMessage = "Menu tidak ditemukan: "+ wrapper.getMenuId();
            }
            else{
                errorMessage = e.getMessage();
            }
            return new DataResponse<GroupMenuWrapper>(false, errorMessage);
        }
    }
    @PutMapping(path = "/update")
    public DataResponse<GroupMenuWrapper> update(@RequestBody GroupMenuWrapper wrapper){
        try {
            return new DataResponse<GroupMenuWrapper>(groupMenuService.save(wrapper));    
        } catch (Exception e) {
            String errorMessage;
            if (e.getMessage().contains("GroupMenu"))
                errorMessage = "Group menu tidak ditemukan: "+ wrapper.getGroupMenuId();
            else if(e.getMessage().contains("Group")){
                errorMessage = "Group tidak ditemukan: "+ wrapper.getGroupId();
            }
            else if(e.getMessage().contains("Menu")){
                errorMessage = "Menu tidak ditemukan: "+ wrapper.getMenuId();
            }
            else{
                errorMessage = e.getMessage();
            }
            return new DataResponse<GroupMenuWrapper>(false, errorMessage);
        }
        
    }
    @PutMapping(path = "/updateByGroupIdAndMenuId")
    public DataResponse<GroupMenuWrapper> updateByGroupIdAndMenuId(@RequestParam Long groupId,@RequestParam Long menuId, @RequestParam Character isActive){
        try{
            return new DataResponse<GroupMenuWrapper>(groupMenuService.saveByGroupIdAndMenuId(groupId, menuId, isActive));
        } catch (Exception e) {
            return new DataResponse<GroupMenuWrapper>(false, "Hak akses tidak ditemukan: groupId="+ groupId +", groupId="+menuId);
        }
    }
    @DeleteMapping(path = "/delete")
    public DataResponse<GroupMenuWrapper> delete(@RequestParam Long id){
        try {
            groupMenuService.delete(id);
            return new DataResponse<GroupMenuWrapper>(true,"Data berhasil dihapus");    
        } catch (Exception e) {
            return new DataResponse<GroupMenuWrapper>(false, "Group menu tidak ditemukan: "+id);
        }
        
    }
}
