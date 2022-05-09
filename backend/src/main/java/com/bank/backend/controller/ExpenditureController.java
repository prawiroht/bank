package com.bank.backend.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bank.backend.entity.Expenditure;
import com.bank.backend.service.ExpenditureService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.ExpenditureWrapper;

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


@CrossOrigin
@RestController
@RequestMapping (path = "/expenditure")
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;

    @GetMapping (path = "/findAll")
    public DataResponseList<ExpenditureWrapper> findAll(){
        return new DataResponseList<ExpenditureWrapper>(expenditureService.findAll());
    }

    @GetMapping (path = "/findById")
    public DataResponse<ExpenditureWrapper> findById(@RequestParam("id") Long id){
        try {
            ExpenditureWrapper expenditure = expenditureService.findById(id);
            return new DataResponse<ExpenditureWrapper>(expenditure);
        } catch (Exception e){
            return new DataResponse<ExpenditureWrapper>(false, "Data with ID "+ id +" not found");
        }
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<ExpenditureWrapper, Expenditure> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<ExpenditureWrapper, Expenditure> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findAllCategories(all, page, size));
    }

    // @GetMapping (path = "/findAllWithRequestStatus")
    // public DataResponsePagination<ExpenditureWrapper, Expenditure> findAllWithRequestStatus(int page, int size) {
    //     return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findAllWithRequestStatus(page, size));
    // }

    @GetMapping (path = "/findByRequestStatus")
    public DataResponsePagination<ExpenditureWrapper, Expenditure> findByRequestStatus(int page, int size){
        return new DataResponsePagination<ExpenditureWrapper, Expenditure>(expenditureService.findByResquestStatus(page, size));
    }

    @PostMapping (path = "/post")
    public DataResponse<ExpenditureWrapper> post(@RequestBody ExpenditureWrapper wrapper){
        return new DataResponse<ExpenditureWrapper>(expenditureService.save(wrapper));
    }

    @PutMapping (path = "/put")
    public DataResponse<ExpenditureWrapper> put(@RequestBody ExpenditureWrapper wrapper){
        try {
            return new DataResponse<ExpenditureWrapper>(expenditureService.save(wrapper));
        } catch (Exception e) {
            String errorMessage;
            if (e.getMessage().contains("Expenditure")){
                errorMessage = "Expenditure with ID "+ wrapper.getExpenditureId() +" is not found";
            } else if (e.getMessage().contains("Bank")){
                errorMessage = "Bank with ID "+ wrapper.getBankId() +" is not found";
            } else if (e.getMessage().contains("University")){
                errorMessage = "University with ID "+ wrapper.getUniversityId() +" is not found";
            } else if (e.getMessage().contains("Purchase")){
                errorMessage = "Purchase with ID "+ wrapper.getPurchaseId() +" is not found";
            } else if (e.getMessage().contains("AccountType")){
                errorMessage = "Account Type with ID "+ wrapper.getAccountTypeId() +" is not found";
            } else if (e.getMessage().contains("Fund")){
                errorMessage = "Fund with ID "+ wrapper.getFundId() +" is not found";
            } else if (e.getMessage().contains("Status")){
                errorMessage = "Status with ID "+ wrapper.getStatusId() +" is not found";
            } else if (e.getMessage().contains("User")){
                errorMessage = "User with ID "+ wrapper.getUserId() +" is not found";
            } else {
               errorMessage = e.getMessage();
            }
            return new DataResponse<ExpenditureWrapper>(false, errorMessage);
        }
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<ExpenditureWrapper> delete(@RequestParam("id") Long id){
        try {
            expenditureService.delete(id);
            return new DataResponse<ExpenditureWrapper>(true, "Delete Successful");
        } catch (Exception e) {
            return new DataResponse<ExpenditureWrapper>(false, "Data not found: "+ id);
        }
    }

    // export csv
    @GetMapping(path = "/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<ExpenditureWrapper> listExpenditure = expenditureService.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Expenditure ID", "Bank ID", "University ID", "Account Number", "Mutation ID", "Transaction Date", "Value", "Purchase ID", "Account Type ID", "Fund ID", "Description", "Status ID", "User ID"};
        String[] nameMapping = {"expenditureId", "bankId", "universityId", "accountNumber", "mutationId", "transactionDate", "value", "purchaseId", "accountTypeId", "fundId", "description", "statusId", "userId"};

        csvWriter.writeHeader(csvHeader);
         
        for (ExpenditureWrapper expenditure : listExpenditure) {
            csvWriter.write(expenditure, nameMapping);
        }
        csvWriter.close();
    }


}
