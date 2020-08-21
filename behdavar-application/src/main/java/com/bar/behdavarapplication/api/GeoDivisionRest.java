package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.GeoDivisionBusiness;
import com.bar.behdavarbackend.dto.GeoDivisionDto;
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
@RequestMapping("/geo-division")
@Validated
public class GeoDivisionRest {

    @Autowired
    GeoDivisionBusiness geoDivisionBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<GeoDivisionDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(geoDivisionBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody GeoDivisionDto dto) {
        return new ResponseEntity<>(geoDivisionBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid GeoDivisionDto dto) {
        geoDivisionBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        geoDivisionBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(geoDivisionBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
