package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.InputExcelGuarantorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputExcelGuarantorRepository extends AbstractRepository<InputExcelGuarantorEntity, Long> {
    List<InputExcelGuarantorEntity> findByInputExcelId(Long inputExcelId);

    InputExcelGuarantorEntity findByInputExcelIdAndContractNumber(Long inputExcelId, String contractNumber);
}
