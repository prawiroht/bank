package com.bank.backend.controller;

import com.bank.backend.entity.Container;
// import com.bank.backend.entity.Status;
// import com.bank.backend.repository.ContainerRepository;
import com.bank.backend.service.ContainerService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.ContainerWrapper;

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
@RequestMapping(path = "/container")
public class ContainerController {
    
    @Autowired
    ContainerService containerService;

    // FIND ALL
    @GetMapping(path = "/findAll")
    public DataResponseList<ContainerWrapper> findAll() {
        return new DataResponseList<ContainerWrapper>(containerService.findAll());
    }

    // FIND ALL WITH PAGINATION
    @GetMapping(path = "/findAllWithPagination")
    public DataResponsePagination<ContainerWrapper, Container> findAllWithPagination(@RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<ContainerWrapper, Container>(containerService.findAllWithPagination(page, size));
    }

    // FIND BY STATUS
    // @GetMapping(path = "/findByStatus")
    // public DataResponsePagination<ContainerWrapper, Container> findByStatus(@RequestParam("status") String status, @RequestParam("page") int page, @RequestParam("size") int size){
    //     return new DataResponsePagination<ContainerWrapper, Container>(containerService.findAllByStatus(status, page, size));
    // }

    // FIND BY STATUS QUERY METHOD
    @GetMapping(path = "/findByRequestStatus")
    public DataResponsePagination<ContainerWrapper, Container> findByRequestStatus(int page, int size){
        return new DataResponsePagination<ContainerWrapper, Container>(containerService.findByRequestStatus(page, size));
    }

    // FIND BY ID
    @GetMapping (path = "/findByContainerId")
    public DataResponse<ContainerWrapper> findById(@RequestParam("id") Long id){
        try {
            ContainerWrapper container = containerService.findByContainerId(id);
            return new DataResponse<ContainerWrapper>(container);
        } catch (Exception e) {
            return new DataResponse<ContainerWrapper>(false, "Container dengan id " + id + " tidak ditemukan");
        }
        
    }

    // FIND BY ALL CATEGORIES
    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<ContainerWrapper, Container> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<ContainerWrapper, Container>(containerService.findAllCategories(all, page, size));
    }

    // FIND ALL CATEGORIES BY STATUS
    // @GetMapping(path = "/findAllCategoriesByStatus")
    // public DataResponsePagination<ContainerWrapper, Container> findAllCategoriesByStatus(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam int size){
    //     return new DataResponsePagination<ContainerWrapper, Container>(containerService.findByStatusAllCategories(all, page, size));
    // }

    // POST
    @PostMapping(path = "/post")
    public DataResponse<ContainerWrapper> post(@RequestBody ContainerWrapper wrapper){
        return new DataResponse<ContainerWrapper>(containerService.save(wrapper));
    }

    // PUT
    @PutMapping(path = "/put")
    public DataResponse<ContainerWrapper> put(@RequestBody ContainerWrapper wrapper){
        try {
            return new DataResponse<ContainerWrapper>(containerService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if (e.getMessage().contains("Container")){
                errorMessage = "Container dengan ID " + wrapper.getContainerId() +" tidak ditemukan";
            } else if (e.getMessage().contains("University")){
                errorMessage = "Univeristas dengan ID " + wrapper.getUniversityId() +" tidak ditemukan";
            } else if (e.getMessage().contains("Bank")){
                errorMessage = "Bank dengan ID " + wrapper.getBankId() +" tidak ditemukan";
            } else if (e.getMessage().contains("Purchase")){
                errorMessage = "Purchase dengan ID " + wrapper.getPurchaseId() +" tidak ditemukan";
            } else if (e.getMessage().contains("AccountType")){
                errorMessage = "Account Type dengan ID " + wrapper.getAccountTypeId() +" tidak ditemukan";
            } else if (e.getMessage().contains("Fund")){
                errorMessage = "Fund dengan ID " + wrapper.getFundId() +" tidak ditemukan";
            } else if (e.getMessage().contains("Status")){
                errorMessage = "Status dengan ID " + wrapper.getStatusId() +" tidak ditemukan";
            } else if (e.getMessage().contains("User")){
                errorMessage = "User dengan ID " + wrapper.getUserId() +" tidak ditemukan";
            } else {
                errorMessage = e.getMessage();
            }
            return new DataResponse<ContainerWrapper>(false, errorMessage);
        }
    }

    // DELETE
    @DeleteMapping(path = "/delete")
    public DataResponse<ContainerWrapper> delete(@RequestParam("containerId") Long containerId){
        try {
            containerService.delete(containerId);
            return new DataResponse<ContainerWrapper>(true, "Delete Success");
        } catch (Exception e) {
            return new DataResponse<ContainerWrapper>(false, "Container dengan id " + containerId + " tidak ditemukan");
        }
        
    }

}
