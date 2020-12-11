package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.MessageBusiness;
import com.bar.behdavarbackend.dto.MessageDto;
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
import java.util.List;

@RestController
@RequestMapping("/api/message")
@Validated
public class MessageRest {

    @Autowired
    MessageBusiness messageBusiness;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_VIEW + "')")
    @PostMapping("/find-by-id")
    public ResponseEntity<MessageDto> findById(@RequestBody @NotNull Long id) {
        return new ResponseEntity<>(messageBusiness.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SAVE_DRAFT + "')")
    @PostMapping("/save-draft")
    public ResponseEntity<Long> saveDraft(@Valid @RequestBody MessageDto dto) {
        return new ResponseEntity<>(messageBusiness.saveDraft(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_VIEW + "')")
    @PostMapping("/inbox")
    public ResponseEntity<List<MessageDto>> getInbox() {
        return new ResponseEntity<>(messageBusiness.getInbox(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SEND + "')")
    @PostMapping("/send")
    public ResponseEntity<Long> send(@Valid @RequestBody MessageDto dto) {
        return new ResponseEntity<>(messageBusiness.send(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid MessageDto dto) {
        messageBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_DELETE + "')")
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        messageBusiness.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SEARCH + "')")
    @PostMapping("/find-paging")
    public ResponseEntity<PagingResponse> findById(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(messageBusiness.findPaging(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SEARCH + "')")
    @PostMapping("/drafts")
    public ResponseEntity<PagingResponse> getDrafts(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(messageBusiness.getDrafts(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SEARCH + "')")
    @PostMapping("/sent-items")
    public ResponseEntity<PagingResponse> getSentItems(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(messageBusiness.getSentItems(pageRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.MESSAGE_SEARCH + "')")
    @PostMapping("/inbox-paging")
    public ResponseEntity<PagingResponse> getInbox(@RequestBody PagingRequest pageRequest) {
        return new ResponseEntity<>(messageBusiness.getInbox(pageRequest), HttpStatus.OK);
    }
}
