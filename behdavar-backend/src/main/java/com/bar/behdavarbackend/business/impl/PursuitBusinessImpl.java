package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.AttachmentBusiness;
import com.bar.behdavarbackend.business.api.PaymentBusiness;
import com.bar.behdavarbackend.business.api.PursuitBusiness;
import com.bar.behdavarbackend.business.api.UserAmountBusiness;
import com.bar.behdavarbackend.business.transformer.AttachmentTransformer;
import com.bar.behdavarbackend.business.transformer.PaymentTransformer;
import com.bar.behdavarbackend.business.transformer.PursuitTransformer;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.PursuitEntity;
import com.bar.behdavardatabase.repository.PursuitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(PursuitBusinessImpl.BEAN_NAME)
public class PursuitBusinessImpl implements PursuitBusiness {
    public static final String BEAN_NAME = "PursuitBusinessImpl";

    private final PaymentBusiness paymentBusiness;
    private final AttachmentBusiness attachmentBusiness;
    private final UserAmountBusiness userAmountBusiness;
    private final PursuitRepository pursuitRepository;

    public PursuitBusinessImpl(PaymentBusiness paymentBusiness, AttachmentBusiness attachmentBusiness, UserAmountBusiness userAmountBusiness, PursuitRepository pursuitRepository) {
        this.paymentBusiness = paymentBusiness;
        this.attachmentBusiness = attachmentBusiness;
        this.userAmountBusiness = userAmountBusiness;
        this.pursuitRepository = pursuitRepository;
    }

    @Override
    public PursuitDto findById(Long id) {
        PursuitEntity pursuitEntity = pursuitRepository.findById(id).orElseThrow(() -> new BusinessException("error.Pursuit.not.found", id));
        return PursuitTransformer.entityToDto(pursuitEntity, new PursuitDto());
    }

    @Override
    @Transactional
    public Long save(PursuitDto dto) {
        Long paymentId = null;
        Long attachmentId = null;
        if (dto.getPayment() != null) {
            if (dto.getPayment().getAttachment() != null) {
                dto.getPayment().getAttachment().setContract(null);
                attachmentId = attachmentBusiness.save(dto.getPayment().getAttachment());
            }

            Optional.ofNullable(attachmentId).ifPresent(id ->
                    dto.getPayment().setAttachment(AttachmentTransformer.createDtoForRelation(id)));
            paymentId = paymentBusiness.save(dto.getPayment());
            userAmountBusiness.increaseReceiveAmount(dto.getPayment().getAmount());
        }
        Optional.ofNullable(paymentId).ifPresent(id ->
                dto.setPayment(PaymentTransformer.createDtoForRelation(id)));

        PursuitEntity pursuitEntity = PursuitTransformer.dtoToEntity(dto, new PursuitEntity());
        pursuitEntity.setId(pursuitRepository.save(pursuitEntity).getId());
        return pursuitEntity.getId();
    }

    @Override
    @Transactional
    public void update(PursuitDto dto) {
        PursuitEntity pursuitEntity = PursuitTransformer.dtoToEntity(dto, pursuitRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Pursuit.not.found", dto.getId())));
        pursuitRepository.save(pursuitEntity);
    }

    @Override
    public void delete(Long id) {
        pursuitRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<PursuitEntity, Long> executor = new PagingExecutor<>(pursuitRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<PursuitEntity> data = (List<PursuitEntity>) pagingResponse.getData();
            List<PursuitDto> output = new ArrayList<>();
            data.forEach(e -> output.add(PursuitTransformer.entityToDto(e, new PursuitDto())));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Override
    public PursuitDto findByContractId(Long id) {
        PursuitEntity pursuitEntity = pursuitRepository.findFirstByContractId(id);
        return pursuitEntity != null ? PursuitTransformer.entityToDto(pursuitEntity, new PursuitDto()) : null;
    }
}
