package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

import java.math.BigDecimal;

@ExcelSheet("تسهیلات")
@Data
public class InputExcelLendingDto extends BaseAuditorDto<String, Long> {
    @ExcelRow
    private int rowIndex;
    @ExcelCell(0)
    private Integer rowNo;
    @ExcelCell(1)
    private String contractNumber;
    @ExcelCell(2)
    private String accountNumber;
    @ExcelCell(3)
    private Integer legal;
    @ExcelCell(4)
    private String branch;
    @ExcelCell(5)
    private Integer branchCode;
    @ExcelCell(6)
    private String contractType;
    @ExcelCell(7)
    private Long contractTypeCode;
    @ExcelCell(8)
    private String receiveDate;
    @ExcelCell(9)
    private String dueDate;
    @ExcelCell(10)
    private String inputDate;
    @ExcelCell(11)
    private Long amount;
    @ExcelCell(12)
    private BigDecimal installmentAmount;
    @ExcelCell(13)
    private Long installmentCount;
    @ExcelCell(14)
    private BigDecimal remainDebtAmount;
    @ExcelCell(15)
    private BigDecimal debtAmount;
    @ExcelCell(16)
    private Long lateFees;
    @ExcelCell(17)
    private Long differedInstallmentCount;
    @ExcelCell(18)
    private String lastPayInstallmentDate;
    @ExcelCell(19)
    private String description;
    @ExcelCell(20)
    private String expert;
    @ExcelCell(21)
    private Long expertCode;
    @ExcelCell(22)
    private Long mortgageAmount;
    @ExcelCell(23)
    private String mortgageDate;
    @ExcelCell(24)
    private Long pledgeType;
    @ExcelCell(25)
    private Long pledgeCode;
    @ExcelCell(26)
    private String bailName;
    @ExcelCell(27)
    private String bailTel;
    @ExcelCell(28)
    private String bailAddress;
    @ExcelCell(28)
    private Long machineType;
    @ExcelCell(30)
    private String machine;
    @ExcelCell(31)
    private String shasiNumber;
    @ExcelCell(32)
    private String motorNumber;
    @ExcelCell(33)
    private String plaqueNumber;
    private InputExcelDto inputExcel;
}
