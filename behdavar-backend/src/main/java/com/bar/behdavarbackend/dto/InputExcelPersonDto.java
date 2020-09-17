package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;
import lombok.Data;


@Data
public abstract class InputExcelPersonDto extends BaseAuditorDto<String, Long> {
    @ExcelRow
    private int rowIndex;
    @ExcelCell(0)
    private Integer rowNo;
    @ExcelCell(1)
    private String contractNumber;
    @ExcelCell(2)
    private String name;
    @ExcelCell(3)
    private String lastName;
    @ExcelCell(4)
    private String nationalCode;
    @ExcelCell(5)
    private String identificationCode;
    @ExcelCell(6)
    private String birthPlace;
    @ExcelCell(7)
    private String birthDate;
    @ExcelCell(8)
    private String fatherName;
    @ExcelCell(9)
    private String address;
    @ExcelCell(10)
    private String mobile1;
    @ExcelCell(11)
    private Long mobile2;
    @ExcelCell(12)
    private long tel1;
    @ExcelCell(13)
    private Long tel2;

    private InputExcelDto inputExcel;
}
