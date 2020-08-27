package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.CustomerBusiness;
import com.bar.behdavarbackend.business.transformer.CustomerTransformer;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.pagination.PagingExecutor;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CustomerEntity;
import com.bar.behdavardatabase.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(CustomerBusinessImpl.BEAN_NAME)
public class CustomerBusinessImpl implements CustomerBusiness {
    public static final String BEAN_NAME = "CustomerBusinessImpl";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new BusinessException("error.Customer.not.found", id));
    }

    @Override
    public Long save(CustomerDto dto) {
        CustomerEntity customerEntity = CustomerTransformer.DTO_TO_ENTITY(dto, new CustomerEntity());
        return customerRepository.save(customerEntity).getId();
    }

    @Override
    public void update(CustomerDto dto) {
        CustomerEntity customerEntity = CustomerTransformer.DTO_TO_ENTITY(dto, findById(dto.getId()));
        customerRepository.save(customerEntity);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> findByContract(Long contractId) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<CustomerEntity> allByContractId = customerRepository.findByContractId(contractId);
        if (!CollectionUtils.isEmpty(allByContractId)) {
            allByContractId.forEach(e -> {
                customerDtos.add(CustomerTransformer.ENTITY_TO_DTO(e, new CustomerDto()));
            });
        }
        return customerDtos;
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor executor = new PagingExecutor(customerRepository, pagingRequest);
        return executor.execute();
    }
}
