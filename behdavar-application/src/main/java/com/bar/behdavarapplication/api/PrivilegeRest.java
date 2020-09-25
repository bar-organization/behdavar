package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PrivilegeBusiness;
import com.bar.behdavarbackend.dto.PrivilegeDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavarcommon.AuthorityConstant;
import org.hibernate.validator.constraints.Length;
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
import java.util.List;

@RestController
@RequestMapping("/api/privilege")
@Validated
public class PrivilegeRest {

    @Autowired
    PrivilegeBusiness PrivilegeBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.PRIVILEGE_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<PrivilegeDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(PrivilegeBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.PRIVILEGE_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(PrivilegeBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.PRIVILEGE_SUGGESTION + "')")
    @PostMapping("/find-suggestion")
    public ResponseEntity<List<PrivilegeDto>> findSuggestion(@RequestBody @Length(min = 3, max = 30) String suggest) {
        return new ResponseEntity(PrivilegeBusiness.findSuggestion(suggest), HttpStatus.OK);
    }
}
