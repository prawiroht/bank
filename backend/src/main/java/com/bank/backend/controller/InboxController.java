package com.bank.backend.controller;

import com.bank.backend.entity.Inbox;
import com.bank.backend.service.InboxService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.InboxWrapper;

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
@RequestMapping(path = "/inbox")
public class InboxController {
    @Autowired
    InboxService inboxService;

    @GetMapping("/findAll")
    public DataResponseList<InboxWrapper> findAll(){
        return new DataResponseList<InboxWrapper>(inboxService.findAll());
    }
    @GetMapping("/findAllPagination")
    public DataResponsePagination<InboxWrapper, Inbox> findAllPagination(@RequestParam int page,@RequestParam int size){
        return new DataResponsePagination<InboxWrapper,Inbox>(inboxService.findAllPagination(page, size));
    }
    @GetMapping("/findByUserId")
    public DataResponseList<InboxWrapper> findByUserId(@RequestParam Long userId){
        try {
            return new DataResponseList<InboxWrapper>(inboxService.findByUserId(userId));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("User"))
                errorMessage = "User tidak ditemukan: " + userId; 
            else
                errorMessage = e.getMessage();
            return new DataResponseList<InboxWrapper>(false,errorMessage);
        }
    }
    @GetMapping("/findByUserIdPagination")
    public DataResponsePagination<InboxWrapper,Inbox> findByUserIdPagination(@RequestParam Long userId, @RequestParam int page, @RequestParam int size){
        try {
            return new DataResponsePagination<InboxWrapper,Inbox>(inboxService.findByUserIdPagination(userId, page, size));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("User"))
                errorMessage = "User tidak ditemukan: " + userId; 
            else
                errorMessage = e.getMessage();
            return new DataResponsePagination<InboxWrapper,Inbox>(false,errorMessage);
        }
    }

    @PostMapping("/post")
    public DataResponse<InboxWrapper> post(@RequestBody InboxWrapper wrapper){
        try {
            return new DataResponse<InboxWrapper>(inboxService.save(wrapper));    
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("User"))
                errorMessage = "User tidak ditemukan: "+ wrapper.getUserId();
            else if(e.getMessage().contains("Transaction"))
                errorMessage = "Transaksi tidak ditemukan: "+ wrapper.getTransactionId();
            else 
                errorMessage = e.getMessage();
            return new DataResponse<InboxWrapper>(false,errorMessage);
        }
    }
    @PutMapping("/update")
    public DataResponse<InboxWrapper> update(@RequestBody InboxWrapper wrapper){
        try {
            return new DataResponse<InboxWrapper>(inboxService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("Inbox")) 
                errorMessage = "Pesan inbox tidak ditemukan: "+ wrapper.getInboxId();
            else if(e.getMessage().contains("User"))
                errorMessage = "User tidak ditemukan: "+ wrapper.getUserId();
            else if(e.getMessage().contains("Transaction"))
                errorMessage = "Transaksi tidak ditemukan: "+ wrapper.getTransactionId();
            else 
                errorMessage = e.getMessage();
            return new DataResponse<InboxWrapper>(false,errorMessage);
        }
    }
    @DeleteMapping("/delete")
    public DataResponse<InboxWrapper> delete(@RequestParam Long id){
        try {
            inboxService.delete(id);
            return new DataResponse<InboxWrapper>(true, "Data berhasil dihapus");
        } catch (Exception e) {
            return new DataResponse<InboxWrapper>(false,"Pesan inbox tidak ditemukan: "+id);
        }
    }  
}
