package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavardatabase.entity.PaymentEntity;

public class PaymentTransformer {

    public static PaymentEntity DTO_TO_ENTITY(PaymentDto dto, PaymentEntity entity) {

        return entity;
    }

    public static PaymentDto ENTITY_TO_DTO(PaymentEntity entity, PaymentDto dto) {
        dto.setId(entity.getId());
        return dto;
    }

    public static PaymentEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
