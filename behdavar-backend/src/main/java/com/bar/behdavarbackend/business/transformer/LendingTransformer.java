package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavarbackend.dto.LendingDto;
import com.bar.behdavardatabase.entity.LendingEntity;

import java.util.Optional;

public class LendingTransformer extends BaseAuditorTransformer {

    public static LendingEntity DTO_TO_ENTITY(LendingDto dto, LendingEntity entity) {
        Optional.ofNullable(dto.getBranchBank()).ifPresent(bankDto -> entity.setBranchBank(BankTransformer.CREATE_ENTITY_FOR_RELATION(bankDto.getId())));
        entity.setIdeaIssueDate(dto.getIdeaIssueDate());
        entity.setMasterAmount(dto.getMasterAmount());
        entity.setDefferedAmount(dto.getDefferedAmount());
        entity.setDefferedCount(dto.defferedCount);
        entity.setLateFees(dto.getLateFees());
        entity.setLendingNumber(dto.lendingNumber);
        entity.setLateFees(dto.getLateFees());
        entity.setReceiveLendingDate(dto.getReceiveLendingDate());
        return entity;
    }

    public static LendingDto ENTITY_TO_DTO(LendingEntity entity, LendingDto dto) {
        transformAuditingFields(entity, dto);
        Optional.ofNullable(entity.getBranchBank()).ifPresent(branchEntity -> dto.setBranchBank(BankTransformer.ENTITY_TO_DTO(branchEntity, new BankDto())));
        dto.setIdeaIssueDate(entity.getIdeaIssueDate());
        dto.setMasterAmount(entity.getMasterAmount());
        dto.setDefferedAmount(entity.getDefferedAmount());
        dto.setDefferedCount(entity.getDefferedCount());
        dto.setLateFees(entity.getLateFees());
        dto.setLendingNumber(entity.getLendingNumber());
        dto.setLateFees(entity.getLateFees());
        dto.setReceiveLendingDate(entity.getReceiveLendingDate());
        return dto;
    }

    public static LendingEntity CREATE_ENTITY_FOR_RELATION(Long id) {
        LendingEntity entity = new LendingEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }
}
