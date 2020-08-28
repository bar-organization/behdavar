package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.BankDto;
import com.bar.behdavarbackend.dto.LendingDto;
import com.bar.behdavardatabase.entity.LendingEntity;

import java.util.Optional;

public class LendingTransformer {

    public static LendingEntity DTO_TO_ENTITY(LendingDto dto, LendingEntity entity) {
        Optional.ofNullable(dto.getBranchBank()).ifPresent(bankDto -> entity.setBranchBank(BankTransformer.CREATE_ENTITY_FOR_RELATION(bankDto.getId())));
        entity.setIdeaIssueDate(dto.getIdeaIssueDate());
        entity.setMasterAmount(dto.getMasterAmount());
        entity.setReceiveLendingDate(dto.getReceiveLendingDate());
        return entity;
    }

    public static LendingDto ENTITY_TO_DTO(LendingEntity entity, LendingDto dto) {
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        Optional.ofNullable(entity.getBranchBank()).ifPresent(branchEntity -> dto.setBranchBank(BankTransformer.ENTITY_TO_DTO(branchEntity, new BankDto())));
        dto.setIdeaIssueDate(entity.getIdeaIssueDate());
        dto.setMasterAmount(entity.getMasterAmount());
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
