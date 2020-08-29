package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.PursuitType;
import com.bar.behdavarcommon.enumeration.ResultType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class PursuitLogDto extends BaseAuditorDto<String, Long> {

    private String description;
    private Boolean coordinateAppointment;
    private Boolean depositAppointment;
    private Boolean submitAccordingFinal;
    private LocalDate nextPursuitDate;
    private Boolean customerDeposit;
    private PursuitType pursuitType;
    private ResultType resultType;
    private PaymentDto payment;
    private UserDto user;
    private ContractDto contract;
    private PursuitDto pursuit;
}