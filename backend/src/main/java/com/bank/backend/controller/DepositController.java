package com.bank.backend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bank.backend.entity.Deposit;
import com.bank.backend.service.DepositService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.DepositWrapper;

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

@CrossOrigin
@RestController
@RequestMapping(value = "/deposit")
public class DepositController {
    @Autowired
    DepositService depositService;
    String errorMassage;

    @PostMapping(path = "/posts")
    public DataResponse<DepositWrapper> save(@RequestBody DepositWrapper wrapper) {
        return new DataResponse<>(depositService.save(wrapper));
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<DepositWrapper> findAll() {
        return new DataResponseList<>(depositService.findAll());
    }

    @GetMapping(path = "/findById")
    public DataResponse<DepositWrapper> findById(@RequestParam Long id) {
        try {
            DepositWrapper result = depositService.getDepositById(id);
            return new DataResponse<>(result);
        } catch (Exception e) {
            return new DataResponse<>(false, "Current Account not found with id: " + id);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long depositId) {
        depositService.delete(depositId);
    }

    @PutMapping(path = "/update")
    public DataResponse<DepositWrapper> update(@RequestBody DepositWrapper wrapper) {
        try {
            return new DataResponse<>(depositService.save(wrapper));
        } catch (Exception e) {
            if (e.getMessage().contains("Deposit"))
                errorMassage = "Current Account not found with id: " + wrapper.getBankId();
            else if (e.getMessage().contains("University"))
                errorMassage = "University not found with id: " + wrapper.getUniversityId();
            else if (e.getMessage().contains("Bank"))
                errorMassage = "Bank not found with id: " + wrapper.getBankId();
            else if (e.getMessage().contains("Period"))
                errorMassage = "Account type not found with id: " + wrapper.getPeriodId();
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
    public DataResponsePagination<DepositWrapper, Deposit> getAllCategories(
            @RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<>(
                depositService.getAllCategories(all, page, size));
    }

    @GetMapping(path = "/getTotalDeposit")
    public Long getTotalDeposit() {
        return depositService.sumNominalWithStatusApprove();
    }

    @GetMapping(path = "/getTotalDepositWithParam")
    public Long getTotalDepositWithParam(@RequestParam @DateTimeFormat(iso = ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE)Date endDate,
            @RequestParam Long bankId) {
        return depositService.sumNominalWithParam(startDate, endDate, bankId);
    }

    @GetMapping(path = "/findByRequestStatus")
    public DataResponsePagination<DepositWrapper, Deposit> findByRequestStatus(int page, int size){
        return new DataResponsePagination<>(depositService.findByRequestStatus(page, size));
    }

    @GetMapping(path = "/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey,headerValue);

        List<DepositWrapper> listContainer = depositService.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Deposit Id", "University ID","Deposit Name", "Bank ID", "Code","Bank Name", "Account Number", "Period ID","Period", "Nominal", "Earning Interest", "Start Date", "Due Date", "Status ID","Status Name", "User ID","User Name"};
        String[] nameMapping = {"depositId", "universityId","depositName", "bankId","code","bankName", "accountNumber", "periodId","period", "nominal","earningInterest","startDate","dueDate","statusId","statusName", "userId","userName"};

        csvWriter.writeHeader(csvHeader);

        for (DepositWrapper container : listContainer) {
            csvWriter.write(container, nameMapping);
        }

        csvWriter.close();

    }
}
