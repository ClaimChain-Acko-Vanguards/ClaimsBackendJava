package com.acko.ClaimChain.ClaimChain.controller;

import com.acko.ClaimChain.ClaimChain.service.ExcelReader;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor

public class ClaimsController {

    private ExcelReader excelReader;
    @PostMapping("/claims/upload")
    private void uploadExcel(MultipartFile file) throws Exception{
        excelReader.readExcel(file);
    }

}
