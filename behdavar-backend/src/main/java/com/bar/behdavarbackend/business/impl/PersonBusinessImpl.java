package com.bar.behdavarbackend.business.impl;

import com.bar.behdavarbackend.business.api.PersonBusiness;
import com.bar.behdavarbackend.business.transformer.PersonTransformer;
import com.bar.behdavarbackend.dto.ContactDto;
import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavardatabase.entity.ContactEntity;
import com.bar.behdavardatabase.entity.PersonEntity;
import com.bar.behdavardatabase.repository.ContactRepository;
import com.bar.behdavardatabase.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(PersonBusinessImpl.BEAN_NAME)
public class PersonBusinessImpl implements PersonBusiness {
    public static final String BEAN_NAME = "PersonBusinessImpl";

    private final PersonRepository repository;
    private final ContactRepository contactRepository;

    public PersonBusinessImpl(PersonRepository repository, ContactRepository contactRepository) {
        this.repository = repository;
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public void update(PersonDto dto) {
        PersonEntity oldEntity = repository.findById(dto.getId()).orElseThrow(() -> new BusinessException("error.Person.not.found", dto.getId()));
        List<Long> oldContactIds = new ArrayList<>();
        List<Long> newContactIds = new ArrayList<>();
        Optional.ofNullable(oldEntity.getContacts()).
                ifPresent(contactEntities -> oldContactIds.addAll(contactEntities.stream().map(ContactEntity::getId).collect(Collectors.toCollection(ArrayList::new))));
        Optional.ofNullable(dto.getContacts()).
                ifPresent(contactDtos -> newContactIds.addAll(contactDtos.stream().filter(contactDto -> contactDto.getId() != null)
                        .map(ContactDto::getId)
                        .collect(Collectors.toCollection(ArrayList::new))));

        PersonEntity personEntity = PersonTransformer.dtoToEntity(dto, oldEntity);
        repository.save(personEntity);
        oldContactIds.removeAll(newContactIds);
        if (!oldContactIds.isEmpty()) {
            oldContactIds.forEach(aLong -> contactRepository.deleteById(aLong));
        }
    }

    @Override
    public List<PersonDto> findSuggestion(String suggest) {
        List<PersonEntity> personEntities = repository.findSuggestion(suggest);
        List<PersonDto> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(personEntities)) {
            personEntities.forEach(e -> result.add(PersonTransformer.entityToDto(e, new PersonDto())));
        }
        return result;
    }

    public PersonEntity findByNationalCode(String nationalCode) {
        return repository.findByNationalCode(nationalCode);
    }

    public Long save(PersonEntity personEntity) {
        return repository.save(personEntity).getId();
    }

}
