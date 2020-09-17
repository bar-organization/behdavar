package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PersonDto;
import com.bar.behdavardatabase.entity.PersonEntity;

import java.util.List;

public interface PersonBusiness {

    void update(PersonDto dto);

    List<PersonDto> findSuggestion(String suggest);

    PersonEntity findByNationalCode(String nationalCode);

    Long save(PersonEntity personEntity);
}
