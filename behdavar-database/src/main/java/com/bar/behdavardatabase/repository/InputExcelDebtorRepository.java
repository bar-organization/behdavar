package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.InputExcelDebtorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputExcelDebtorRepository extends AbstractRepository<InputExcelDebtorEntity, Long> {
    List<InputExcelDebtorEntity> findByInputExcelId(Long inputExcelId);

    InputExcelDebtorEntity findByInputExcelIdAndContractNumber(Long inputExcelId, String contractNumber);
}
