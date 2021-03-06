package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.UserAmountBusiness;
import com.bar.behdavarbackend.dto.UserAmountDto;
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
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user-amount")
@Validated
public class UserAmountRest {

    @Autowired
    UserAmountBusiness userAmountBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.USER_AMOUNT_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<UserAmountDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(userAmountBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.USER_AMOUNT_INCREASE_RECEIVE_AMOUNT + "')")
    @PostMapping("/increase-receive-amount")
    public ResponseEntity<Void> increaseReceiveAmount(@RequestBody @NotNull BigDecimal amount) {
        userAmountBusiness.increaseReceiveAmount(amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.USER_AMOUNT_SAVE + "')")
    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody UserAmountDto dto) {
        return new ResponseEntity<>(userAmountBusiness.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.USER_AMOUNT_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid UserAmountDto dto) {
        userAmountBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.USER_AMOUNT_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(userAmountBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
