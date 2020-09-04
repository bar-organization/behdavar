package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.util.SecurityUtil;

import java.util.Optional;

public class PursuitTransformer extends BaseAuditorTransformer {

    public static PursuitEntity DTO_TO_ENTITY(PursuitDto dto, PursuitEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setCoordinateAppointment(dto.getCoordinateAppointment());
        entity.setCustomerDeposit(dto.getCustomerDeposit());
        entity.setDepositAppointment(dto.getDepositAppointment());
        entity.setDescription(dto.getDescription());
        entity.setNextPursuitDate(dto.getNextPursuitDate());
        entity.setPursuitType(dto.getPursuitType());
        entity.setResultType(dto.getResultType());
        entity.setSubmitAccordingFinal(dto.getSubmitAccordingFinal());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(SecurityUtil.getCurrentUserId()));
        Optional.ofNullable(dto.getPayment()).ifPresent(paymentDto -> entity.setPayment(PaymentTransformer.CREATE_ENTITY_FOR_RELATION(paymentDto.getId())));
        return entity;
    }

    public static PursuitDto ENTITY_TO_DTO(PursuitEntity entity, PursuitDto dto) {
        transformAuditingFields(entity, dto);
        dto.setCoordinateAppointment(entity.getCoordinateAppointment());
        dto.setCustomerDeposit(entity.getCustomerDeposit());
        dto.setDepositAppointment(entity.getDepositAppointment());
        dto.setDescription(entity.getDescription());
        dto.setNextPursuitDate(entity.getNextPursuitDate());
        dto.setPursuitType(entity.getPursuitType());
        dto.setResultType(entity.getResultType());
        dto.setSubmitAccordingFinal(entity.getSubmitAccordingFinal());
        Optional.ofNullable(entity.getPayment()).ifPresent(e -> dto.setPayment(PaymentTransformer.ENTITY_TO_DTO(e, new PaymentDto())));
        dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        return dto;
    }

    public static PursuitEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PursuitEntity entity = new PursuitEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PursuitDto CREATE_DTO_FOR_RELATION(Long id) {
        PursuitDto dto = new PursuitDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
