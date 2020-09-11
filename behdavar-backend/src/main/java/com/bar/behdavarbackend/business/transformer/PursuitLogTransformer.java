package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.dto.PursuitLogDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.entity.PursuitLogEntity;

import java.util.Optional;

public class PursuitLogTransformer extends BaseAuditorTransformer {

    public static PursuitLogEntity dtoToEntity(PursuitLogDto dto, PursuitLogEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setCoordinateAppointment(dto.getCoordinateAppointment());
        entity.setCustomerDeposit(dto.getCustomerDeposit());
        entity.setDepositAppointment(dto.getDepositAppointment());
        entity.setDescription(dto.getDescription());
        entity.setNextPursuitDate(dto.getNextPursuitDate());
        Optional.ofNullable(dto.getPayment()).ifPresent(paymentDto -> PaymentTransformer.createEntityForRelation(paymentDto.getId()));
        entity.setPursuitType(dto.getPursuitType());
        entity.setResultType(dto.getResultType());
        entity.setSubmitAccordingFinal(dto.getSubmitAccordingFinal());
        entity.setUser(UserTransformer.createEntityForRelation(dto.getUser().getId()));
        entity.setPursuit(PursuitTransformer.createEntityForRelation(dto.getPursuit().getId()));
        return entity;
    }

    public static PursuitLogDto entityToDto(PursuitLogEntity entity, PursuitLogDto dto) {
        transformAuditingFields(entity, dto);
        dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        dto.setCoordinateAppointment(entity.getCoordinateAppointment());
        dto.setCustomerDeposit(entity.getCustomerDeposit());
        dto.setDepositAppointment(entity.getDepositAppointment());
        dto.setDescription(entity.getDescription());
        dto.setNextPursuitDate(entity.getNextPursuitDate());
        Optional.ofNullable(entity.getPayment()).ifPresent(e -> PaymentTransformer.entityToDto(e, new PaymentDto()));
        dto.setPursuitType(entity.getPursuitType());
        dto.setResultType(entity.getResultType());
        dto.setSubmitAccordingFinal(entity.getSubmitAccordingFinal());
        dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        dto.setPursuit(PursuitTransformer.entityToDto(entity.getPursuit(), new PursuitDto()));
        return dto;
    }


    public static PursuitLogEntity pursuitEntityToLogEntity(PursuitEntity pursuitEntity, PursuitLogEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(pursuitEntity.getContract().getId()));
        entity.setCoordinateAppointment(pursuitEntity.getCoordinateAppointment());
        entity.setCustomerDeposit(pursuitEntity.getCustomerDeposit());
        entity.setDepositAppointment(pursuitEntity.getDepositAppointment());
        entity.setDescription(pursuitEntity.getDescription());
        entity.setNextPursuitDate(pursuitEntity.getNextPursuitDate());
        Optional.ofNullable(pursuitEntity.getPayment()).ifPresent(paymentDto -> PaymentTransformer.createEntityForRelation(paymentDto.getId()));
        entity.setPursuitType(pursuitEntity.getPursuitType());
        entity.setResultType(pursuitEntity.getResultType());
        entity.setSubmitAccordingFinal(pursuitEntity.getSubmitAccordingFinal());
        entity.setUser(UserTransformer.createEntityForRelation(pursuitEntity.getUser().getId()));
        entity.setPursuit(PursuitTransformer.createEntityForRelation(pursuitEntity.getId()));
        return entity;
    }


    public static PursuitLogEntity createEntityForRelation(Long id) {
        PursuitLogEntity entity = new PursuitLogEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
