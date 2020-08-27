package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.AttachmentBusiness;
import com.bar.behdavarbackend.dto.AttachmentDto;
import com.bar.behdavarbackend.util.pagination.PagingRequest;
import com.bar.behdavarbackend.util.pagination.PagingResponse;
import com.bar.behdavardatabase.entity.AttachmentEntity;
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
import java.util.List;

@RestController
@RequestMapping("/attachment")
@Validated
public class AttachmentRest {

    @Autowired
    AttachmentBusiness attachmentBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<AttachmentEntity> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(attachmentBusiness.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody AttachmentDto dto) {
        return new ResponseEntity<>(attachmentBusiness.save(dto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid AttachmentDto dto) {
        attachmentBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        attachmentBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(attachmentBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PostMapping("/find-by-contract")
    public ResponseEntity<List<AttachmentDto>> findByContract(@NotNull @RequestBody Long contractId) {
        return new ResponseEntity<>(attachmentBusiness.findByContract(contractId), HttpStatus.OK);
    }
}
