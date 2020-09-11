package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.InputExcelLendingDto;
import com.bar.behdavardatabase.entity.InputExcelLendingEntity;

public class InputExcelLendingTransformer extends BaseAuditorTransformer {

    public static InputExcelLendingEntity dtoToEntity(InputExcelLendingDto dto, InputExcelLendingEntity entity) {
        entity.setRowIndex(dto.getRowIndex());
        entity.setRowNo(dto.getRowNo());
        entity.setContractNumber(dto.getContractNumber());
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setLegal(dto.getLegal());
        entity.setBranch(dto.getBranch());
        entity.setBranchCode(dto.getBranchCode());
        entity.setContractType(dto.getContractType());
        entity.setContractTypeCode(dto.getContractTypeCode());
        entity.setReceiveDate(dto.getReceiveDate());
        entity.setDueDate(dto.getDueDate());
        entity.setInputDate(dto.getInputDate());
        entity.setAmount(dto.getAmount());
        entity.setInstallmentAmount(dto.getInstallmentAmount());
        entity.setInstallmentCount(dto.getInstallmentCount());
        entity.setRemainDebtAmount(dto.getRemainDebtAmount());
        entity.setDebtAmount(dto.getDebtAmount());
        entity.setLateFees(dto.getLateFees());
        entity.setDifferedInstallmentCount(dto.getDifferedInstallmentCount());
        entity.setLastPayInstallmentDate(dto.getLastPayInstallmentDate());
        entity.setDescription(dto.getDescription());
        entity.setExpert(dto.getExpert());
        entity.setExpertCode(dto.getExpertCode());
        entity.setMortgageAmount(dto.getMortgageAmount());
        entity.setMortgageDate(dto.getMortgageDate());
        entity.setPledgeType(dto.getPledgeType());
        entity.setPledgeCode(dto.getPledgeCode());
        entity.setBailName(dto.getBailName());
        entity.setBailTel(dto.getBailTel());
        entity.setBailAddress(dto.getBailAddress());
        entity.setMachineType(dto.getMachineType());
        entity.setMachine(dto.getMachine());
        entity.setShasiNumber(dto.getShasiNumber());
        entity.setMotorNumber(dto.getMotorNumber());
        entity.setPlaqueNumber(dto.getPlaqueNumber());
        return entity;
    }

    public static InputExcelLendingDto entityToDto(InputExcelLendingEntity entity, InputExcelLendingDto dto) {
        transformAuditingFields(entity, dto);
        dto.setRowIndex(entity.getRowIndex());
        dto.setRowNo(entity.getRowNo());
        dto.setContractNumber(entity.getContractNumber());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setLegal(entity.getLegal());
        dto.setBranch(entity.getBranch());
        dto.setBranchCode(entity.getBranchCode());
        dto.setContractType(entity.getContractType());
        dto.setContractTypeCode(entity.getContractTypeCode());
        dto.setReceiveDate(entity.getReceiveDate());
        dto.setDueDate(entity.getDueDate());
        dto.setInputDate(entity.getInputDate());
        dto.setAmount(entity.getAmount());
        dto.setInstallmentAmount(entity.getInstallmentAmount());
        dto.setInstallmentCount(entity.getInstallmentCount());
        dto.setRemainDebtAmount(entity.getRemainDebtAmount());
        dto.setDebtAmount(entity.getDebtAmount());
        dto.setLateFees(entity.getLateFees());
        dto.setDifferedInstallmentCount(entity.getDifferedInstallmentCount());
        dto.setLastPayInstallmentDate(entity.getLastPayInstallmentDate());
        dto.setDescription(entity.getDescription());
        dto.setExpert(entity.getExpert());
        dto.setExpertCode(entity.getExpertCode());
        dto.setMortgageAmount(entity.getMortgageAmount());
        dto.setMortgageDate(entity.getMortgageDate());
        dto.setPledgeType(entity.getPledgeType());
        dto.setPledgeCode(entity.getPledgeCode());
        dto.setBailName(entity.getBailName());
        dto.setBailTel(entity.getBailTel());
        dto.setBailAddress(entity.getBailAddress());
        dto.setMachineType(entity.getMachineType());
        dto.setMachine(entity.getMachine());
        dto.setShasiNumber(entity.getShasiNumber());
        dto.setMotorNumber(entity.getMotorNumber());
        dto.setPlaqueNumber(entity.getPlaqueNumber());
        return dto;
    }

    public static InputExcelLendingEntity createEntityForRelation(Long id) {
        InputExcelLendingEntity entity = new InputExcelLendingEntity();
        entity.setId(id);
        entity.setVersion(0L);
        return entity;
    }

    public static InputExcelLendingDto createDtoForRelation(Long id) {
        InputExcelLendingDto dto = new InputExcelLendingDto();
        dto.setId(id);
        dto.setVersion(0L);
        return dto;
    }
}
