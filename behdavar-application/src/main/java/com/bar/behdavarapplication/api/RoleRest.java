package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.RoleBusiness;
import com.bar.behdavarbackend.dto.RoleDto;
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
@RequestMapping("/api/role")
@Validated
public class RoleRest {

    @Autowired
    RoleBusiness roleBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<RoleDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(roleBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody RoleDto dto) {
        return new ResponseEntity<>(roleBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid RoleDto dto) {
        roleBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        roleBusiness.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(roleBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
