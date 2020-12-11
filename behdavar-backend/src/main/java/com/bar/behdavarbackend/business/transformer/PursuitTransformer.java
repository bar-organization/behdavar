package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.util.SecurityUtil;

import java.util.Optional;

public class PursuitTransformer extends BaseAuditorTransformer {

    public static PursuitEntity dtoToEntity(PursuitDto dto, PursuitEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setCoordinateAppointment(dto.getCoordinateAppointment());
        entity.setCustomerDeposit(dto.getCustomerDeposit());
        entity.setDescription(dto.getDescription());
        entity.setNextPursuitDate(dto.getNextPursuitDate());
        entity.setPursuitType(dto.getPursuitType());
        entity.setResultType(dto.getResultType());
        entity.setUser(UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId()));
        Optional.ofNullable(dto.getPayment()).ifPresent(paymentDto -> entity.setPayment(PaymentTransformer.createEntityForRelation(paymentDto.getId())));
        return entity;
    }

    public static PursuitDto entityToDto(PursuitEntity entity, PursuitDto dto) {
        transformAuditingFields(entity, dto);
        dto.setCoordinateAppointment(entity.getCoordinateAppointment());
        dto.setCustomerDeposit(entity.getCustomerDeposit());
        dto.setDescription(entity.getDescription());
        dto.setNextPursuitDate(entity.getNextPursuitDate());
        dto.setPursuitType(entity.getPursuitType());
        dto.setResultType(entity.getResultType());
        Optional.ofNullable(entity.getPayment()).ifPresent(e -> dto.setPayment(PaymentTransformer.entityToDto(e, new PaymentDto())));
        dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        return dto;
    }

    public static PursuitEntity createEntityForRelation(Long id) {
        PursuitEntity entity = new PursuitEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PursuitDto createDtoForRelation(Long id) {
        PursuitDto dto = new PursuitDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
