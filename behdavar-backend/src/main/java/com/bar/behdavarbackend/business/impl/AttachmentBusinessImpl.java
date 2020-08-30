package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.AttachmentBusiness;
import com.bar.behdavarbackend.business.transformer.AttachmentTransformer;
import com.bar.behdavarbackend.dto.AttachmentDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.AttachmentEntity;
import com.bar.behdavardatabase.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(AttachmentBusinessImpl.BEAN_NAME)
public class AttachmentBusinessImpl implements AttachmentBusiness {
    public static final String BEAN_NAME = "AttachmentBusinessImpl";

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public AttachmentEntity findById(Long id) {
        return attachmentRepository.findById(id).orElseThrow(() -> new BusinessException("error.Attachment.not.found", id));
    }

    @Override
    public Long save(AttachmentDto dto) {
        AttachmentEntity attachmentEntity = AttachmentTransformer.DTO_TO_ENTITY(dto, new AttachmentEntity());
        return attachmentRepository.save(attachmentEntity).getId();
    }

    @Override
    public void update(AttachmentDto dto) {
        AttachmentEntity attachmentEntity = AttachmentTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        attachmentRepository.save(attachmentEntity);
    }

    @Override
    public void delete(Long id) {
        attachmentRepository.deleteById(id);
    }

    @Override
    public List<AttachmentDto> findByContract(Long contractId) {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        List<AttachmentEntity> allByContractId = attachmentRepository.findAllByContractId(contractId);
        if (!CollectionUtils.isEmpty(allByContractId)) {
            allByContractId.forEach(e -> {
                attachmentDtos.add(AttachmentTransformer.ENTITY_TO_DTO(e, new AttachmentDto()));
            });
        }
        return attachmentDtos;
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<AttachmentEntity, Long> executor = new PagingExecutor<>(attachmentRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<AttachmentEntity> data = (List<AttachmentEntity>) pagingResponse.getData();
            List<AttachmentDto> output = new ArrayList<>();
            data.forEach(e -> output.add(AttachmentTransformer.ENTITY_TO_DTO(e, new AttachmentDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
