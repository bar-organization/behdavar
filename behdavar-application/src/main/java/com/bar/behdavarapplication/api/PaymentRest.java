package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PaymentBusiness;
import com.bar.behdavarbackend.dto.PaymentDto;
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
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@Validated
public class PaymentRest {

    @Autowired
    PaymentBusiness paymentBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<PaymentDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(paymentBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/find-by-contract")
    public ResponseEntity<List<PaymentDto>> findByContractId(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(paymentBusiness.findByContractId(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody PaymentDto dto) {
        return new ResponseEntity<>(paymentBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid PaymentDto dto) {
        paymentBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(paymentBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
