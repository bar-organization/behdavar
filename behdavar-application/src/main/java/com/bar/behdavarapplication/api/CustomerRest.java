package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CustomerBusiness;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavarcommon.AuthorityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerRest {

    @Autowired
    CustomerBusiness customerBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<CustomerDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(customerBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_SAVE + "')")
    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerBusiness.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerDto dto) {
        customerBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_DELETE + "')")
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        customerBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(customerBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CUSTOMER_VIEW_BY_CONTRACT + "')")
    @PostMapping("/find-by-contract")
    public ResponseEntity<List<CustomerDto>> findByContract(@NotNull @RequestBody Long contractId) {
        return new ResponseEntity<>(customerBusiness.findByContract(contractId), HttpStatus.OK);
    }
}
