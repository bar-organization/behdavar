package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.PersonDto;

import java.util.List;

public interface PersonBusiness {

    void update(PersonDto dto);

    List<PersonDto> findSuggestion(String suggest);
}
