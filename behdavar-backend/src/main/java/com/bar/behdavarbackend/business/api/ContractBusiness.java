package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.ContractDto;
import com.bar.behdavarcommon.enumeration.ContractStatus;

public interface ContractBusiness {
    ContractDto findById(Long id);

    void updateStatus(Long contractId, ContractStatus newStatus);
}
