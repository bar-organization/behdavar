package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarbackend.dto.StatusLogDto;
import com.bar.behdavarbackend.dto.UserDto;
import com.bar.behdavardatabase.entity.StatusLogEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusLogTransformer extends BaseAuditorTransformer {

    public static StatusLogEntity dtoToEntity(StatusLogDto dto, StatusLogEntity entity) {
        entity.setContract(ContractTransformer.createEntityForRelation(dto.getContract().getId()));
        entity.setStatus(dto.getStatus());
        entity.setUser(UserTransformer.createEntityForRelation(dto.getUser().getId()));
        return entity;
    }

    public static StatusLogDto entityToDto(StatusLogEntity entity, StatusLogDto dto, String... strings) {
        List<String> fields = Arrays.stream(strings).collect(Collectors.toList());
        transformAuditingFields(entity, dto);
        dto.setStatus(entity.getStatus());
        if (fields.contains(StatusLogEntity.CONTRACT)) {
            dto.setContract(ContractTransformer.entityToDto(entity.getContract(), new ContractDto()));
        } else {
            dto.setContract(ContractTransformer.createDtoForRelation(entity.getContract().getId()));
        }

        if (fields.contains(StatusLogEntity.USER)) {
            dto.setUser(UserTransformer.entityToDto(entity.getUser(), new UserDto()));
        } else {
            dto.setUser(UserTransformer.createDtoForRelation(entity.getUser().getId()));
        }


        return dto;
    }

    public static StatusLogEntity createEntityForRelation(Long id) {
        StatusLogEntity entity = new StatusLogEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static StatusLogDto createDtoForRelation(Long id) {
        StatusLogDto dto = new StatusLogDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
