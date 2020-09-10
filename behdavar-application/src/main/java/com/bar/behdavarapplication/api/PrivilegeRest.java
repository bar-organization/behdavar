package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PrivilegeBusiness;
import com.bar.behdavarbackend.dto.PrivilegeDto;
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
@RequestMapping("/api/privilege")
@Validated
public class PrivilegeRest {

    @Autowired
    PrivilegeBusiness PrivilegeBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<PrivilegeDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(PrivilegeBusiness.findById(id), HttpStatus.OK);
    }


    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(PrivilegeBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
