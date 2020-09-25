package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.GuarantorBusiness;
import com.bar.behdavarbackend.dto.GuarantorDto;
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
@RequestMapping("/api/guarantor")
@Validated
public class GuarantorRest {

    @Autowired
    GuarantorBusiness GuarantorBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<GuarantorDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(GuarantorBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_SAVE + "')")
    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody GuarantorDto dto) {
        return new ResponseEntity<>(GuarantorBusiness.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid GuarantorDto dto) {
        GuarantorBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_DELETE + "')")
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        GuarantorBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(GuarantorBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.GUARANTOR_VIEW_BY_CONTRACT + "')")
    @PostMapping("/find-by-contract")
    public ResponseEntity<List<GuarantorDto>> findByContract(@NotNull @RequestBody Long contractId) {
        return new ResponseEntity<>(GuarantorBusiness.findByContract(contractId), HttpStatus.OK);
    }
}
