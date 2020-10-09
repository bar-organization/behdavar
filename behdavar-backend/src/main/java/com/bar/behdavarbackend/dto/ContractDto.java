package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ContractDto extends BaseAuditorDto<String, Long> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String contractNumber;
    private LocalDate submitDate;
    private LendingDto lending;
    private ProductDto product;
    private CatalogDetailDto corporation;
    private ContractStatus contractStatus;
    private ContractType contractType;
    private Set<CustomerDto> customers = new HashSet<>();
}