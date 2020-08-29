package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.StatusLogDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.StatusLogEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusLogTransformer {

    public static StatusLogEntity DTO_TO_ENTITY(StatusLogDto dto, StatusLogEntity entity) {
        entity.setContract(ContractTransformer.CREATE_ENTITY_FOR_RELATION(dto.getContract().getId()));
        entity.setStatus(dto.getStatus());
        entity.setUser(UserTransformer.CREATE_ENTITY_FOR_RELATION(dto.getUser().getId()));
        return entity;
    }

    public static StatusLogDto ENTITY_TO_DTO(StatusLogEntity entity, StatusLogDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setStatus(entity.getStatus());
        if (fields.contains(StatusLogEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.ENTITY_TO_DTO(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.CREATE_DTO_FOR_RELATION(entity.getContract().getId()));
        }

        if (fields.contains(StatusLogEntity.USER)) {
            dto.setUser(UserTransformer.ENTITY_TO_DTO(entity.getUser(), new UserDto()));
        } else {
            dto.setUser(UserTransformer.CREATE_DTO_FOR_RELATION(entity.getUser().getId()));
        }


        return dto;
    }

    public static StatusLogEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        StatusLogEntity entity = new StatusLogEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static StatusLogDto CREATE_DTO_FOR_RELATION(Long id) {
        StatusLogDto dto = new StatusLogDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
