package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CustomerBusiness;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerRest {

    @Autowired
    CustomerBusiness customerBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<CustomerEntity> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(customerBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerDto dto) {
        customerBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        customerBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(customerBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PostMapping("/find-by-contract")
    public ResponseEntity<List<CustomerDto>> findByContract(@NotNull @RequestBody Long contractId) {
        return new ResponseEntity<>(customerBusiness.findByContract(contractId), HttpStatus.OK);
    }
}
