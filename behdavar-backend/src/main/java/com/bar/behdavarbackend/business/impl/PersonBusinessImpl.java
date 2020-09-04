package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PersonBusiness;
import com.bar.behdavarbackend.business.transformer.PersonTransformer;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavardatabase.entity.PersonEntity;
import com.bar.behdavardatabase.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(PersonBusinessImpl.BEAN_NAME)
public class PersonBusinessImpl implements PersonBusiness {
    public static final String BEAN_NAME = "PersonBusinessImpl";

    private final PersonRepository repository;

    public PersonBusinessImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void update(PersonDto dto) {
        PersonEntity personEntity = PersonTransformer.DTO_TO_ENTITY(dto, repository.findById(dto.getId()).orElseThrow(() -> new BusinessException("error.Person.not.found", dto.getId())));
        repository.save(personEntity);
    }

}
