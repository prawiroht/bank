package com.bank.backend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bank.backend.entity.CurrentAccount;
import com.bank.backend.service.CurrentAccountService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.CurrentAccountWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/currentaccount")
public class CurrentAccountController {
    @Autowired
    CurrentAccountService currentAccountService;

    String errorMassage;

    @PostMapping(path = "/posts")
    public DataResponse<CurrentAccountWrapper> save(@RequestBody CurrentAccountWrapper wrapper) {
        return new DataResponse<>(currentAccountService.save(wrapper));
    }

    @GetMapping(path = "/findById")
    public DataResponse<CurrentAccountWrapper> findById(@RequestParam Long id) {
        try {
            CurrentAccountWrapper hasil = currentAccountService.getCurrentAccountById(id);
            return new DataResponse<>(hasil);
        } catch (Exception e) {
            return new DataResponse<>(false, "Current Account not found with id: " + id);
        }
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<CurrentAccountWrapper> findAll() {
        return new DataResponseList<>(currentAccountService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long currentAccountId) {
        currentAccountService.delete(currentAccountId);
    }

    @PutMapping(path = "/update")
    public DataResponse<CurrentAccountWrapper> update(@RequestBody CurrentAccountWrapper wrapper) {
        try {
            return new DataResponse<>(currentAccountService.save(wrapper));
        } catch (Exception e) {
            if (e.getMessage().contains("CurrentAccount"))
                errorMassage = "Current Account not found with id: " + wrapper.getCurrentAccountId();
            else if (e.getMessage().contains("University"))
                errorMassage = "University not found with id: " + wrapper.getUniversityId();
            else if (e.getMessage().contains("Bank"))
                errorMassage = "Bank not found with id: " + wrapper.getBankId();
            else if (e.getMessage().contains("AccountType"))
                errorMassage = "Account type not found with id: " + wrapper.getAccountTypeId();
            else if (e.getMessage().contains("User"))
                errorMassage = "User not found with id: " + wrapper.getUserId();
            else if (e.getMessage().contains("Status"))
                errorMassage = "Status not found with id: " + wrapper.getStatusId();
            else {
                errorMassage = e.getMessage();
            }
            return new DataResponse<>(false, errorMassage);
        }

    }

    @GetMapping(path = "/getAllCategories")
    public DataResponsePagination<CurrentAccountWrapper, CurrentAccount> getByAllCategories(
            @RequestParam("all") String all,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<>(
                currentAccountService.getAllCategories(all, page, size));
    }

    @GetMapping(path = "/getTotalCurrentAccount")
    public Long getTotalCurrentAccount() {
        return currentAccountService.sumNominalWithStatusApprove();
    }

    @GetMapping(path = "/getTotalCurrentAccountWithParam")
    public Long getTotalCurrentAccountWithParam(@RequestParam @DateTimeFormat(iso = ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endDate,
            @RequestParam Long bankId) {
        return currentAccountService.sumNominalWithParam(startDate, endDate, bankId);
    }

    @GetMapping(path = "/findByRequestStatus")
    public DataResponsePagination<CurrentAccountWrapper, CurrentAccount> findByRequestStatus(int page, int size){
        return new DataResponsePagination<>(currentAccountService.findByRequestStatus(page, size));
    }

//    Export CSV
    @GetMapping(path = "/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey,headerValue);

        List<CurrentAccountWrapper> listContainer = currentAccountService.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Current Account Id", "University ID","University Name", "Bank ID", "Code","Bank Name", "Account Number", "Account Type ID","Account Type Name", "Initial Balance Date", "Status ID","Status Name", "User ID","User Name"};
        String[] nameMapping = {"currentAccountId", "universityId","universityName", "bankId","code","bankName", "accountNumber", "accountTypeId","accountTypeName", "initialBalanceDate","statusId","statusName", "userId","userName"};

        csvWriter.writeHeader(csvHeader);

        for (CurrentAccountWrapper container : listContainer) {
            csvWriter.write(container, nameMapping);
        }

        csvWriter.close();

    }
}
