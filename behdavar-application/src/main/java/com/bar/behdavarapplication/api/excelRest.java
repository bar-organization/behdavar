package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/excel")
@Validated
public class excelRest {

    @Autowired
    ExcelReaderBusiness excelReaderBusiness;

    @PostMapping("/upload")
    public ResponseEntity<CartableDto> findById(@RequestBody @Valid InputExcelDto dto) {
        excelReaderBusiness.readAndSave(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
