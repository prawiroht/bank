package com.bank.backend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bank.backend.entity.Fund;
import com.bank.backend.service.FundService;
import com.bank.backend.util.DataResponse;
import com.bank.backend.util.DataResponseList;
import com.bank.backend.util.DataResponsePagination;

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
@RequestMapping (path = "/fund")
public class FundController {
    @Autowired
    FundService fundService;

    @GetMapping (path = "/findAll")
    public DataResponseList<Fund> findAll(){
        return new DataResponseList<Fund>(fundService.findAll());
    }

    @GetMapping (path = "/findAllPagination")
    public DataResponsePagination<Fund, Fund> findAllPagination(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new DataResponsePagination<Fund, Fund>(fundService.findAllPagination(page, size));
    };

    @GetMapping (path ="/findByAllCategories")
    public DataResponsePagination<Fund, Fund> findByAllCategories(@RequestParam("all") String all, @RequestParam("page") int page, @RequestParam("size") int size){
        return new DataResponsePagination<Fund, Fund>(fundService.findAllCategories(all, page, size));
    }

    //input
    @PostMapping (path = "/input")
    public DataResponse<Fund> post(@RequestBody Fund fund){
        return new DataResponse<Fund>(fundService.save(fund));
    }

    //update
    @PutMapping (path = "/update")
    public DataResponse<Fund> put(@RequestBody Fund fund){
        return new DataResponse<Fund>(fundService.save(fund));
    }

    //delete
    @DeleteMapping (path = "/delete")
    public DataResponse<Fund> delete(@RequestParam("id") Long id){
        fundService.delete(id);
            return new DataResponse<Fund>(true, "Delete Success");
    }

    //Export CSV
    @GetMapping(path = "/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Sumber_Dana" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Fund> listFund = fundService.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"No", "Alias", "Nama"};
        String[] nameMapping = {"fundId", "alias", "fundName"};

        csvWriter.writeHeader(csvHeader);
         
        for (Fund fund : listFund) {
            csvWriter.write(fund, nameMapping);
        }
        csvWriter.close();
    }
}
