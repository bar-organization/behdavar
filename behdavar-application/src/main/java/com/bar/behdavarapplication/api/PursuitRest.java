package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PursuitBusiness;
import com.bar.behdavarbackend.dto.PursuitDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
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

@RestController
@RequestMapping("/api/pursuit")
@Validated
public class PursuitRest {

    @Autowired
    PursuitBusiness pursuitBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<PursuitDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(pursuitBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/find-by-contract")
    public ResponseEntity<PursuitDto> findByContractId(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(pursuitBusiness.findByContractId(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody PursuitDto dto) {
        return new ResponseEntity<>(pursuitBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid PursuitDto dto) {
        pursuitBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        pursuitBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(pursuitBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
