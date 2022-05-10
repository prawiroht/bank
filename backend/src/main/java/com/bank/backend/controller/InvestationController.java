package com.bank.backend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bank.backend.entity.Investation;
import com.bank.backend.service.InvestationService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;
import com.bank.backend.wrapper.DepositWrapper;
import com.bank.backend.wrapper.InvestationWrapper;

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
@RequestMapping(path = "/investment")
public class InvestationController {
    @Autowired
    InvestationService investationService;

    @PostMapping(path = "/posts")
    public DataResponse<InvestationWrapper> save(@RequestBody InvestationWrapper wrapper) {
        return new DataResponse<>(investationService.save(wrapper));
    }

    @GetMapping(path = "/findAll")
    public DataResponseList<InvestationWrapper> findAll() {
        return new DataResponseList<>(investationService.findAll());
    }

    @GetMapping(path = "/findById")
    public DataResponse<InvestationWrapper> findById(@RequestParam Long id) {
        try {
            InvestationWrapper result = investationService.getInvestationById(id);
            return new DataResponse<>(result);
        } catch (Exception e) {
            return new DataResponse<>(false, "Current Account not found with id: " + id);
        }
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long InvestationId) {
        investationService.delete(InvestationId);
    }

    @PutMapping(path = "/update")
    public DataResponse<InvestationWrapper> update(@RequestBody InvestationWrapper wrapper) {
        return new DataResponse<>(investationService.save(wrapper));
    }

    @GetMapping(path = "/getAllCategories")
    public DataResponsePagination<InvestationWrapper, Investation> getByAllCategories(
            @RequestParam("all") String all,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return new DataResponsePagination<>(
                investationService.findAllCategoriesWithPagination(all, page, size));
    }

    @GetMapping(path = "/getTotalInvestment")
    public Long getTotalInvestment() {
        return investationService.sumNominalWithStatusApprove();
    }

    @GetMapping(path = "/getTotalInvestmentWithParam")
    public Long getTotalInvestmentWithParam(@RequestParam @DateTimeFormat(iso = ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endDate,
            @RequestParam Long bankId) {
        return investationService.sumNominalWithParam(startDate, endDate, bankId);
    }

    @GetMapping(path = "/findByRequestStatus")
    public DataResponsePagination<InvestationWrapper, Investation> findByRequestStatus(int page, int size){
        return new DataResponsePagination<>(investationService.findByRequestStatus(page, size));
    }

    @GetMapping(path = "/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey,headerValue);

        List<InvestationWrapper> listContainer = investationService.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Investment Id", "University ID","Investment Name", "Initial NAB", "Initial Unit","Initial Value","Market NAB","Market Unit", "Market Value","Market Value", "Start Date", "Bank ID", "Bank Name", "Status ID","Status Name", "User ID","User Name"};
        String[] nameMapping = {"investationId", "universityId","investationName", "initialNAB","initialUnit","initialValue", "marketNAB", "marketUnit","marketValue", "startDate","bankId","bankName","statusId","statusName", "userId","userName"};

        csvWriter.writeHeader(csvHeader);

        for (InvestationWrapper container : listContainer) {
            csvWriter.write(container, nameMapping);
        }

        csvWriter.close();

    }
}
