package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PursuitLogBusiness;
import com.bar.behdavarbackend.dto.PursuitLogDto;
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

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/pursuit-log")
@Validated
public class PursuitLogRest {

    @Autowired
    PursuitLogBusiness pursuitLogBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<PursuitLogDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(pursuitLogBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(pursuitLogBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
