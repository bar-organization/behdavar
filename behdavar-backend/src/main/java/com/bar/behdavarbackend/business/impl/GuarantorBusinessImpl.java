package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.GuarantorBusiness;
import com.bar.behdavarbackend.business.transformer.GuarantorTransformer;
import com.bar.behdavarbackend.dto.GuarantorDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.GuarantorEntity;
import com.bar.behdavardatabase.repository.GuarantorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(GuarantorBusinessImpl.BEAN_NAME)
public class GuarantorBusinessImpl implements GuarantorBusiness {
    public static final String BEAN_NAME = "GuarantorBusinessImpl";

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Override
    public GuarantorDto findById(Long id) {
        GuarantorEntity entity = guarantorRepository.findById(id).orElseThrow(() -> new BusinessException("error.Guarantor.not.found", id));
        return GuarantorTransformer.ENTITY_TO_DTO(entity, new GuarantorDto());
    }

    @Override
    public Long save(GuarantorDto dto) {
        GuarantorEntity GuarantorEntity = GuarantorTransformer.DTO_TO_ENTITY(dto, new GuarantorEntity());
        return guarantorRepository.save(GuarantorEntity).getId();
    }

    @Override
    public void update(GuarantorDto dto) {
        GuarantorEntity GuarantorEntity = GuarantorTransformer.DTO_TO_ENTITY(dto, guarantorRepository.findById(dto.getId()).orElseThrow(() -> new BusinessException("error.Guarantor.not.found", dto.getId())));
        guarantorRepository.save(GuarantorEntity);
    }

    @Override
    public void delete(Long id) {
        guarantorRepository.deleteById(id);
    }

    @Override
    public List<GuarantorDto> findByContract(Long contractId) {
        List<GuarantorDto> guarantorDtos = new ArrayList<>();
        List<GuarantorEntity> allByContractId = guarantorRepository.findAllByContractId(contractId);
        if (!CollectionUtils.isEmpty(allByContractId)) {
            allByContractId.forEach(e -> {
                guarantorDtos.add(GuarantorTransformer.ENTITY_TO_DTO(e, new GuarantorDto(), GuarantorEntity.CONTRACT, GuarantorEntity.PERSON));
            });
        }
        return guarantorDtos;
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(guarantorRepository, pagingRequest);
        return executor.execute();
    }
}
