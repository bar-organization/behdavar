package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PaymentBusiness;
import com.bar.behdavarbackend.business.transformer.PaymentTransformer;
import com.bar.behdavarbackend.dto.PaymentDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.PaymentEntity;
import com.bar.behdavardatabase.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service(PaymentBusinessImpl.BEAN_NAME)
public class PaymentBusinessImpl implements PaymentBusiness {
    public static final String BEAN_NAME = "PaymentBusinessImpl";

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentDto findById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id).orElseThrow(() -> new BusinessException("error.Payment.not.found", id));
        return PaymentTransformer.entityToDto(paymentEntity, new PaymentDto());
    }

    @Override
    @Transactional
    public Long save(PaymentDto dto) {
        PaymentEntity paymentEntity = PaymentTransformer.dtoToEntity(dto, new PaymentEntity());
        paymentEntity.setId(paymentRepository.save(paymentEntity).getId());
        return paymentEntity.getId();
    }

    @Override
    @Transactional
    public void update(PaymentDto dto) {
        PaymentEntity paymentEntity = PaymentTransformer.dtoToEntity(dto, paymentRepository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("error.Payment.not.found", dto.getId())));
        paymentRepository.save(paymentEntity);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<PaymentEntity, Long> executor = new PagingExecutor<>(paymentRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<PaymentEntity> data = (List<PaymentEntity>) pagingResponse.getData();
            List<PaymentDto> output = new ArrayList<>();
            data.forEach(e -> output.add(PaymentTransformer.entityToDto(e, new PaymentDto(), PaymentEntity.CONTRACT, PaymentEntity.USER)));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }

    @Override
    public List<PaymentDto> findByContractId(Long id) {
        List<PaymentDto> paymentDtos = new ArrayList<>();
        List<PaymentEntity> entities = paymentRepository.findByContractId(id);
        if (!CollectionUtils.isEmpty(entities)) {
            entities.forEach(e ->
                    paymentDtos.add(PaymentTransformer.entityToDto(e, new PaymentDto(), PaymentEntity.CONTRACT, PaymentEntity.USER)));
        }

        return paymentDtos;
    }

    @Override
    public Long getTotalDepositAmount(Long contractId) {
        return paymentRepository.findByContractId(contractId).stream().map(PaymentEntity::getAmount).mapToLong(BigDecimal::longValue).sum();
    }
}
