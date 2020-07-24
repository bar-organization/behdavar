package com.bar.behdavarapplication.api;

import com.bar.behdavarbackend.business.api.CatalogBusiness;
import com.bar.behdavardatabase.entity.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogRest {

    @Autowired
    CatalogBusiness catalogBusiness;

    @PostMapping("/find-by-id")
    public ResponseEntity<CatalogEntity> findById(@RequestBody Long id){
        return new ResponseEntity<>(catalogBusiness.findById(id) , HttpStatus.OK);
    }
}
