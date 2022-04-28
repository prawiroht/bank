package com.bank.backend.controller;

import com.bank.backend.entity.Menu;
import com.bank.backend.service.MenuService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.MenuWrapper;

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
@RequestMapping(path = "/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping(path = "/findAll")
    public DataResponseList<MenuWrapper> findAll(){
        return new DataResponseList<MenuWrapper>(menuService.findAll());
    }
    @GetMapping(path = "/findAllPagination")
    public DataResponsePagination<MenuWrapper,Menu> findAllPagination(@RequestParam int page, @RequestParam int size){
        return new DataResponsePagination<MenuWrapper,Menu>(menuService.findAllPagination(page, size));
    }
    @PostMapping(path = "/post")
    public DataResponse<MenuWrapper> post(@RequestBody MenuWrapper wrapper){
        return new DataResponse<MenuWrapper>(menuService.save(wrapper));
    }
    @PutMapping(path = "/update")
    public DataResponse<MenuWrapper> update(@RequestBody MenuWrapper wrapper){
        try {
            return new DataResponse<MenuWrapper>(menuService.save(wrapper));    
        } catch (Exception e) {
            return new DataResponse<MenuWrapper>(false,"Menu tidak ditemukan: "+wrapper.getMenuId());
        }
        
    }
    @DeleteMapping(path = "/delete")
    public DataResponse<MenuWrapper> delete(@RequestParam Long id){
        try {
            menuService.delete(id);
            return new DataResponse<MenuWrapper>(true, "Data berhasil dihapus.");    
        } catch (Exception e) {
            return new DataResponse<MenuWrapper>(false, "Menu tidak ditemukan: "+ id);
        }
        
    }
}
