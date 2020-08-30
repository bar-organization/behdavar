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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(CustomerBusinessImpl.BEAN_NAME)
public class CustomerBusinessImpl implements CustomerBusiness {
    public static final String BEAN_NAME = "CustomerBusinessImpl";

    private final CustomerRepository customerRepository;

    public CustomerBusinessImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new BusinessException("error.Customer.not.found", id));
        return CustomerTransformer.ENTITY_TO_DTO(customerEntity, new CustomerDto(), CustomerEntity.PERSON);
    }

    @Override
    public Long save(CustomerDto dto) {
        CustomerEntity customerEntity = CustomerTransformer.DTO_TO_ENTITY(dto, new CustomerEntity());
        return customerRepository.save(customerEntity).getId();
    }

    @Override
    public void update(CustomerDto dto) {
        CustomerEntity customerEntity = CustomerTransformer.DTO_TO_ENTITY(dto, customerRepository.findById(dto.getId()).orElseThrow(() -> new BusinessException("error.Customer.not.found", dto.getId())));
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
            allByContractId.forEach(e -> customerDtos.add(CustomerTransformer.ENTITY_TO_DTO(e, new CustomerDto(), CustomerEntity.CONTRACT, CustomerEntity.PERSON)));
        }
        return customerDtos;
    }

    @Override
    public PagingResponse findPaging(PagingRequest pagingRequest) {
        PagingExecutor<CustomerEntity, Long> executor = new PagingExecutor<>(customerRepository, pagingRequest);

        PagingResponse pagingResponse = executor.execute();
        if (pagingResponse.getData() != null) {
            List<CustomerEntity> data = (List<CustomerEntity>) pagingResponse.getData();
            List<CustomerDto> output = new ArrayList<>();
            data.forEach(e -> output.add(CustomerTransformer.ENTITY_TO_DTO(e, new CustomerDto(), CustomerEntity.PERSON)));
            pagingResponse.setData(output);
        }
        return pagingResponse;
    }
}
