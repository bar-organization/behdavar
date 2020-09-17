package com.bar.behdavardatabase.repository;

import com.bar.behdavardatabase.entity.InputExcelLendingEntity;
import com.bar.behdavardatabase.entity.InputExcelPersonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputExcelPersonRepository extends AbstractRepository<InputExcelPersonEntity, Long> {
    List<InputExcelLendingEntity> findByInputExcelId(Long inputExcelId);

    List<InputExcelLendingEntity> findByInputExcelIdAndContractNumber(Long inputExcelId, String contractNumber);
}
