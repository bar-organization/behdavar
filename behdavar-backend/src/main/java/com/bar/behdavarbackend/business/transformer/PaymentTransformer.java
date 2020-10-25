package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.PaymentEntity;
import com.bar.behdavardatabase.util.SecurityUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentTransformer extends BaseAuditorTransformer {

    public static PaymentEntity dtoToEntity(PaymentDto dto, PaymentEntity entity) {
        entity.setAmount(dto.getAmount());
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setPaymentDate(LocalDate.now());
        entity.setPaymentType(dto.getPaymentType());
        entity.setUser(UserTransformer.createEntityForRelation(SecurityUtil.getCurrentUserId()));
        Optional.ofNullable(dto.getAttachment()).
                ifPresent(attachmentDto ->
                        entity.setAttachment(AttachmentTransformer.createEntityForRelation(attachmentDto.getId())));
        return entity;
    }

    public static PaymentDto entityToDto(PaymentEntity entity, PaymentDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentType(entity.getPaymentType());

        if (fields.contains(PaymentEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.entityToDto(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        }

        if (fields.contains(PaymentEntity.USER)) {
            dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        } else {
            dto.setUser(UserTransformer.createDtoForRelation(entity.getUser().getId()));
        }

        Optional.ofNullable(entity.getAttachment()).ifPresent(attachmentEntity ->
                dto.setAttachment(AttachmentTransformer.createDtoForRelation(attachmentEntity.getId())));
        return dto;
    }

    public static PaymentEntity createEntityForRelation(Long id) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static PaymentDto createDtoForRelation(Long id) {
        PaymentDto dto = new PaymentDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
