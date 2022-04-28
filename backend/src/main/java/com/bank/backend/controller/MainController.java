package com.bank.backend.controller;


import com.bank.backend.entity.Main;
import com.bank.backend.service.MainService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.MainWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping (path = "/main")
public class MainController {
    @Autowired
    MainService mainService;

    @GetMapping (path = "/findAll")
    public DataResponseList<MainWrapper> findAll(){
        return new DataResponseList<MainWrapper>(mainService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<MainWrapper, Main> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<MainWrapper, Main>(mainService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<MainWrapper, Main> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<MainWrapper, Main>(mainService.findAllCategories(all, page, size));
    }

    @GetMapping (path = "/findByRequestStatus")
    public DataResponsePagination<MainWrapper, Main> findByRequestStatus(int page, int size){
        return new DataResponsePagination<MainWrapper, Main>(mainService.findByResquestStatus(page, size));
    }

    @DeleteMapping("/{id}")
    public DataResponse<MainWrapper> delete(@RequestParam Long mainId) {
        try {
            mainService.delete(mainId);
            return new DataResponse<MainWrapper>(true,"Data berhasil dihapus");
        } catch (Exception e) {
            return new DataResponse<MainWrapper>(false, "Main Id tidak ditemukan: "+ mainId);
        }        
    }

    @PostMapping("/post")
    public DataResponse<MainWrapper> save(@RequestBody MainWrapper wrapper) {
        return new DataResponse<MainWrapper>(mainService.save(wrapper));
    }

    @PutMapping("/update")
    public DataResponse<MainWrapper> update(@RequestBody MainWrapper wrapper) {
        try {
            return new DataResponse<MainWrapper>(mainService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("University"))
                errorMessage = "University dengan id: "+wrapper.getUniversityId()+" tidak ditemukan";
            else if(e.getMessage().contains("Bank")){
                errorMessage = "Bank dengan id:" +wrapper.getBankId()+" tidak ditemukan";
            }
            else if(e.getMessage().contains("AccountType")){
                errorMessage = "Jenis rekening dengan id: "+wrapper.getAccountTypeId() +" tidak ditemukan";
            }
            else if(e.getMessage().contains("Fund")){
                errorMessage = "Sumber Dana dengan id: "+wrapper.getFundId() +" tidak ditemukan: ";
            }
            else{
                errorMessage=e.getMessage();
            }
            return new DataResponse<MainWrapper>(false, errorMessage);
        }
    }
}

