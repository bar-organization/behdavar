package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.ContractBusiness;
import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarcommon.AuthorityConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/contract")
@Validated
public class ContractRest {

    private final ContractBusiness contractBusiness;

    public ContractRest(ContractBusiness contractBusiness) {
        this.contractBusiness = contractBusiness;
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<ContractDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(contractBusiness.findById(id), HttpStatus.OK);
    }

}
