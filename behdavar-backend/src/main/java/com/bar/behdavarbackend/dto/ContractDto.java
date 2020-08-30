package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ContractDto extends BaseAuditorDto<String, Long> {

    public BigDecimal masterAmount;

    public BigDecimal defferedAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate idealIssueDate;

    public String lendingNumber;

    public Long defferedCount;

    public BigDecimal lateFees;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate submitDate;

    public LendingDto lending;

    public ProductDto product;

    public CatalogDetailDto corporation;

    public ContractStatus contractStatus;

    public ContractType contractType;
}