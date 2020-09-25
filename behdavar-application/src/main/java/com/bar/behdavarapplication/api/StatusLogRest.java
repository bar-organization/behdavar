package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.StatusLogBusiness;
import com.bar.behdavarbackend.dto.StatusLogDto;
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

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/status-log")
@Validated
public class StatusLogRest {

    @Autowired
    StatusLogBusiness StatusLogBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.STATUS_LOG_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<StatusLogDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(StatusLogBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.STATUS_LOG_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(StatusLogBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
