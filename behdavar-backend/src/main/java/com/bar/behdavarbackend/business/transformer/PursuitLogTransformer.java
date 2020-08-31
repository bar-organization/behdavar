package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.dto.PursuitLogDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.entity.PursuitLogEntity;

import java.util.Optional;

public class PursuitLogTransformer extends BaseAuditorTransformer {

    public static PursuitLogEntity DTO_TO_ENTITY(PursuitLogDto dto, PursuitLogEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setCoordinateAppointment(dto.getCoordinateAppointment());
        entity.setCustomerDeposit(dto.getCustomerDeposit());
        entity.setDepositAppointment(dto.getDepositAppointment());
        entity.setDescription(dto.getDescription());
        entity.setNextPursuitDate(dto.getNextPursuitDate());
        Optional.ofNullable(dto.getPayment()).ifPresent(paymentDto -> PaymentTransformer.CREATE_ENTITY_FOR_RELATION(paymentDto.getId()));
        entity.setPursuitType(dto.getPursuitType());
        entity.setResultType(dto.getResultType());
        entity.setSubmitAccordingFinal(dto.getSubmitAccordingFinal());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getUser().getId()));
        entity.setPursuit(PursuitTransformer.CREATE_ENTITY_FOR_RELATION(dto.getPursuit().getId()));
        return entity;
    }

    public static PursuitLogDto ENTITY_TO_DTO(PursuitLogEntity entity, PursuitLogDto dto) {
        transformAuditingFields(entity, dto);
        dto.setId(entity.getId());
        dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        dto.setCoordinateAppointment(entity.getCoordinateAppointment());
        dto.setCustomerDeposit(entity.getCustomerDeposit());
        dto.setDepositAppointment(entity.getDepositAppointment());
        dto.setDescription(entity.getDescription());
        dto.setNextPursuitDate(entity.getNextPursuitDate());
        Optional.ofNullable(entity.getPayment()).ifPresent(e -> PaymentTransformer.ENTITY_TO_DTO(e, new PaymentDto()));
        dto.setPursuitType(entity.getPursuitType());
        dto.setResultType(entity.getResultType());
        dto.setSubmitAccordingFinal(entity.getSubmitAccordingFinal());
        dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        dto.setPursuit(PursuitTransformer.ENTITY_TO_DTO(entity.getPursuit(), new PursuitDto()));
        return dto;
    }


    public static PursuitLogEntity PURSUIT_ENTITY_TO_LOG_ENTITY(PursuitEntity pursuitEntity, PursuitLogEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(pursuitEntity.getContract().getId()));
        entity.setCoordinateAppointment(pursuitEntity.getCoordinateAppointment());
        entity.setCustomerDeposit(pursuitEntity.getCustomerDeposit());
        entity.setDepositAppointment(pursuitEntity.getDepositAppointment());
        entity.setDescription(pursuitEntity.getDescription());
        entity.setNextPursuitDate(pursuitEntity.getNextPursuitDate());
        Optional.ofNullable(pursuitEntity.getPayment()).ifPresent(paymentDto -> PaymentTransformer.CREATE_ENTITY_FOR_RELATION(paymentDto.getId()));
        entity.setPursuitType(pursuitEntity.getPursuitType());
        entity.setResultType(pursuitEntity.getResultType());
        entity.setSubmitAccordingFinal(pursuitEntity.getSubmitAccordingFinal());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(pursuitEntity.getUser().getId()));
        entity.setPursuit(PursuitTransformer.CREATE_ENTITY_FOR_RELATION(pursuitEntity.getId()));
        return entity;
    }


    public static PursuitLogEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PursuitLogEntity entity = new PursuitLogEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
