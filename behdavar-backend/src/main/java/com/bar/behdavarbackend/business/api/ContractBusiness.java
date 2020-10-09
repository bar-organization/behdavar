package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.ContractDto;

public interface ContractBusiness {
    ContractDto findById(Long id);
}
