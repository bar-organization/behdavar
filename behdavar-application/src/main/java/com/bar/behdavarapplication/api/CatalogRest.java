package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CatalogBusiness;
import com.bar.behdavarbackend.dto.CatalogDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.CatalogEntity;
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
@RequestMapping("/api/catalog")
@Validated
public class CatalogRest {

    @Autowired
    CatalogBusiness catalogBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<CatalogEntity> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(catalogBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody CatalogDto dto) {
        return new ResponseEntity<>(catalogBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid CatalogDto dto) {
        catalogBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        catalogBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse<CatalogDto>> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(catalogBusiness.findPaging(pageRequest), HttpStatus.OK);
    }
}
