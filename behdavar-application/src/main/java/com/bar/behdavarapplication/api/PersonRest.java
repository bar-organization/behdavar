package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.PersonBusiness;
import com.bar.behdavarbackend.dto.CustomerDto;
import com.bar.behdavarbackend.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
@Validated
public class PersonRest {
    private final PersonBusiness personBusiness;

    public PersonRest(PersonBusiness personBusiness) {
        this.personBusiness = personBusiness;
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid PersonDto dto) {
        personBusiness.update(dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
