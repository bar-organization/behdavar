package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.ExcelReaderBusiness;
import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.InputExcelDto;
import com.bar.behdavarcommon.AuthorityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/excel")
@Validated
public class ExcelRest {

    @Autowired
    ExcelReaderBusiness excelReaderBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.UPLOAD_EXCEL + "')")
    @PostMapping("/upload")
    public ResponseEntity<CartableDto> findById(@RequestBody @Valid InputExcelDto dto) {
        excelReaderBusiness.readAndSave(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CONVERT + "')")
    @PostMapping("/convert")
    public void convert(@RequestBody @Valid Long inputExcelId) {
        excelReaderBusiness.convert(inputExcelId);
    }
}
