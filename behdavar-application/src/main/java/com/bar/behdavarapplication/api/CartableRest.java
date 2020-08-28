package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CartableBusiness;
import com.bar.behdavarbackend.dto.CartableDto;
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
@RequestMapping("/api/cartable")
@Validated
public class CartableRest {

    @Autowired
    CartableBusiness CartableBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<CartableDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(CartableBusiness.findById(id), HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody CartableDto dto) {
        return new ResponseEntity<>(CartableBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CartableDto dto) {
        CartableBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        CartableBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(CartableBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
