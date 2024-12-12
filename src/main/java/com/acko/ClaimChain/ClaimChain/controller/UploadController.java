package com.acko.ClaimChain.ClaimChain.controller;

import com.acko.ClaimChain.ClaimChain.service.ExcelReader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private ExcelReader excelReader;
    @PostMapping("/excel")
    private void uploadExcel(MultipartFile file) throws Exception{
        excelReader.readExcel(file);
    }


}
