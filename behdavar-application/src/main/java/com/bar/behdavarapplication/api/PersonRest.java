package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PersonBusiness;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavarcommon.AuthorityConstant;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@Validated
public class PersonRest {
    private final PersonBusiness personBusiness;

    public PersonRest(PersonBusiness personBusiness) {
        this.personBusiness = personBusiness;
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.PERSON_UPDATE + "')")
    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid PersonDto dto) {
        personBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('" + AuthorityConstant.PERSON_SUGGESTION + "')")
    @PostMapping("/find-suggestion")
    public ResponseEntity<List<PersonDto>> findSuggestion(@RequestBody @NotBlank @Length(min = 3, max = 30) String suggest) {
        return new ResponseEntity(personBusiness.findSuggestion(suggest), HttpStatus.OK);
    }
}
