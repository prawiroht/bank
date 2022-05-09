package com.bank.backend.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

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

    //delete
    @DeleteMapping("/{id}")
    public DataResponse<MainWrapper> delete(@RequestParam Long mainId) {
        try {
            mainService.delete(mainId);
            return new DataResponse<MainWrapper>(true,"Data berhasil dihapus");
        } catch (Exception e) {
            return new DataResponse<MainWrapper>(false, "Main Id tidak ditemukan: "+ mainId);
        }        
    }
    
    //input
    @PostMapping("/input")
    public DataResponse<MainWrapper> save(@RequestBody MainWrapper wrapper) {
        return new DataResponse<MainWrapper>(mainService.save(wrapper));
    }

    //update
    @PutMapping("/update")
    public DataResponse<MainWrapper> update(@RequestBody MainWrapper wrapper) {
        try {
            return new DataResponse<MainWrapper>(mainService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if(e.getMessage().contains("University"))
                errorMessage = "University dengan id: "+wrapper.getUniversityId()+" tidak ditemukan";
            else if(e.getMessage().contains("Bank")){
                errorMessage = "Bank dengan ID:" +wrapper.getBankId()+" tidak ditemukan";
            }
            else if(e.getMessage().contains("AccountType")){
                errorMessage = "Jenis rekening dengan ID: "+wrapper.getAccountTypeId() +" tidak ditemukan";
            }
            else if(e.getMessage().contains("Fund")){
                errorMessage = "Sumber Dana dengan ID: "+wrapper.getFundId() +" tidak ditemukan: ";
            }
            else if (e.getMessage().contains("Status")){
                errorMessage = "Status dengan ID "+ wrapper.getStatusId() +" tidak ditemukan";
            } else if (e.getMessage().contains("User")){
                errorMessage = "User dengan ID "+ wrapper.getUserId() +" tidak ditemukan";
            }
            else{
                errorMessage=e.getMessage();
            }
            return new DataResponse<MainWrapper>(false, errorMessage);
        }
    }

     // EXPORT CSV
     @GetMapping(path = "/export")
     public void exportToCSV(HttpServletResponse response) throws IOException {
         response.setContentType("text/csv");
         DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
         String currentDateTime = dateFormatter.format(new Date());
 
         String headerKey = "Content-Disposition";
         String headerValue = "attachment; filename=Utama_" + currentDateTime + ".csv";
         response.setHeader(headerKey, headerValue);
 
         List<MainWrapper> listMain = mainService.findAll();
 
         ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
         String[] csvHeader = {"Utama ID", "Universitas ID", "Nama Bank", "No Rekening", "Jenis Rekening", "Mutasi", "Waktu", "Nilai", "Pembelian", "Sumber Dana", "Keterangan", "Status"};
         String[] nameMapping = {"mainId", "bankName", "accountNumber", "accountTypeId", "mutationId", "transactionDate", "value", "purchaseName", "fundName", "description","statusName"};
 
         csvWriter.writeHeader(csvHeader);
          
         for (MainWrapper main : listMain) {
             csvWriter.write(main, nameMapping);
         }
         csvWriter.close();
     }
}

