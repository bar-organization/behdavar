package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class PaymentDto extends BaseAuditorDto<Long, Long> {

    @NotNull(message = "validation.error.not.null")
    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @NotNull(message = "validation.error.not.null")
    private PaymentType paymentType;

    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;

    @NotNull(message = "validation.error.not.null")
    private UserDto user;
}
