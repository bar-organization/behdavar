package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.StatusLogBusiness;
import com.bar.behdavarbackend.dto.StatusLogDto;
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
@RequestMapping("/status-log")
@Validated
public class StatusLogRest {

    @Autowired
    StatusLogBusiness StatusLogBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<StatusLogDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(StatusLogBusiness.findById(id), HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody StatusLogDto dto) {
        return new ResponseEntity<>(StatusLogBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid StatusLogDto dto) {
        StatusLogBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        StatusLogBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(StatusLogBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
