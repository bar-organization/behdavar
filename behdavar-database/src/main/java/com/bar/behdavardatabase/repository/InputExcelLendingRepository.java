package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.InputExcelLendingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputExcelLendingRepository extends AbstractRepository<InputExcelLendingEntity, Long> {
    List<InputExcelLendingEntity> findByInputExcelId(Long inputExcelId);

    List<InputExcelLendingEntity> findByInputExcelIdAndContractNumber(Long inputExcelId, String contractNumber);
}
