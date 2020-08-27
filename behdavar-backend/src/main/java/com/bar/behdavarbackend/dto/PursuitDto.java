package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.PursuitType;
import com.bar.behdavarcommon.enumeration.ResultType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PursuitDto extends BaseAuditorDto<Long, Long> {

    private String description;
    private Boolean coordinateAppointment;
    private Boolean depositAppointment;
    private Boolean submitAccordingFinal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nextPursuitDate;
    private Boolean customerDeposit;
    private PursuitType pursuitType;
    private ResultType resultType;
    private PaymentDto payment;
    private UserDto user;
    private ContractDto contract;
}