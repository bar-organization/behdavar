package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CartableBusiness;
import com.bar.behdavarbackend.dto.AssignContractDto;
import com.bar.behdavarbackend.dto.CartableDto;
import com.bar.behdavarbackend.dto.UserInfoDto;
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

@RestController
@RequestMapping("/api/cartable")
@Validated
public class CartableRest {

    @Autowired
    CartableBusiness CartableBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<CartableDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(CartableBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_GET_USER_INFO + "')")
    @PostMapping("/get-user-info")
    public ResponseEntity<UserInfoDto> getUserInfo() {
        return new ResponseEntity<>(CartableBusiness.getUserInfo(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_SAVE + "')")
    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody CartableDto dto) {
        return new ResponseEntity<>(CartableBusiness.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_ASSIGN + "')")
    @PostMapping("/assign")
    public ResponseEntity<Void> save(@Valid @RequestBody AssignContractDto dto) {
        CartableBusiness.assignContract(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CartableDto dto) {
        CartableBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_DELETE + "')")
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        CartableBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.CARTABLE_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(CartableBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.VIEW_DOCUMENT_MANAGEMENT + "','" + AuthorityConstant.SEARCH_ALL +"')")
    @PostMapping("/find-paging-all")
    public ResponseEntity<PagingResponse> findPagingAll(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(CartableBusiness.findPagingAll(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.VIEW_DOCUMENT_MANAGEMENT + "','" + AuthorityConstant.SEARCH_ALL +"','" + AuthorityConstant.CARTABLE_SEARCH +"')")
    @PostMapping("/find-paging-doc-flow")
    public ResponseEntity<PagingResponse> findPagingDocumentFlow(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(CartableBusiness.findPagingDocumentFlow(pageRequest), HttpStatus.OK);
    }
}
