package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.InputExcelPersonDto;
import com.bar.behdavardatabase.entity.InputExcelPersonEntity;

public class InputExcelPersonTransformer extends BaseAuditorTransformer {

    public static <D extends InputExcelPersonDto, E extends InputExcelPersonEntity> InputExcelPersonEntity DTO_TO_ENTITY(D dto, E entity) {
        entity.setRowIndex(dto.getRowIndex());
        entity.setRowNo(dto.getRowNo());
        entity.setContractNumber(dto.getContractNumber());
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setNationalCode(dto.getNationalCode());
        entity.setIdentificationCode(dto.getIdentificationCode());
        entity.setBirthPlace(dto.getBirthPlace());
        entity.setBirthDate(dto.getBirthDate());
        entity.setFatherName(dto.getFatherName());
        entity.setAddress(dto.getAddress());
        entity.setMobile1(dto.getMobile1());
        entity.setMobile2(dto.getMobile2());
        entity.setTel1(dto.getTel1());
        return entity;
    }

    public static InputExcelPersonDto ENTITY_TO_DTO(InputExcelPersonEntity entity, InputExcelPersonDto dto) {
        transformAuditingFields(entity, dto);
        dto.setRowIndex(entity.getRowIndex());
        dto.setRowNo(entity.getRowNo());
        dto.setContractNumber(entity.getContractNumber());
        dto.setName(entity.getName());
        dto.setLastName(entity.getLastName());
        dto.setNationalCode(entity.getNationalCode());
        dto.setIdentificationCode(entity.getIdentificationCode());
        dto.setBirthPlace(entity.getBirthPlace());
        dto.setBirthDate(entity.getBirthDate());
        dto.setFatherName(entity.getFatherName());
        dto.setAddress(entity.getAddress());
        dto.setMobile1(entity.getMobile1());
        dto.setMobile2(entity.getMobile2());
        dto.setTel1(entity.getTel1());
        return dto;
    }

}
