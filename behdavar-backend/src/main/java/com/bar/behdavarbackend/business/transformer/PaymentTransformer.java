package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PaymentEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentTransformer {

    public static PaymentEntity DTO_TO_ENTITY(PaymentDto dto, PaymentEntity entity) {
        entity.setAmount(dto.getAmount());
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setPaymentType(dto.getPaymentType());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getUser().getId()));
        Optional.ofNullable(dto.getAttachment()).
                ifPresent(attachmentDto ->
                        entity.setAttachment(AttachmentTransformer.CREATE_ENTITY_FOR_RELATION(attachmentDto.getId())));
        return entity;
    }

    public static PaymentDto ENTITY_TO_DTO(PaymentEntity entity, PaymentDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentType(entity.getPaymentType());
        dto.setId(entity.getId());

        if (fields.contains(PaymentEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        }

        if (fields.contains(PaymentEntity.USER)) {
            dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        } else {
            dto.setUser(UserTransformer.CREATE_DTO_FOR_RELATION(entity.getUser().getId()));
        }

        Optional.ofNullable(entity.getAttachment()).ifPresent(attachmentEntity ->
                dto.setAttachment(AttachmentTransformer.CREATE_DTO_FOR_RELATION(attachmentEntity.getId())));
        return dto;
    }

    public static PaymentEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
