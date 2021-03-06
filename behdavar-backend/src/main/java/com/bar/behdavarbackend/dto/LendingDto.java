package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class LendingDto extends BaseAuditorDto<String, Long> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate ideaIssueDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate receiveLendingDate;
    private BankDto branchBank;
    private BigDecimal masterAmount;
    private BigDecimal defferedAmount;
    private String lendingNumber;
    private Long defferedCount;
    private BigDecimal lateFees;
    private BigDecimal remainDebtAmount;
}