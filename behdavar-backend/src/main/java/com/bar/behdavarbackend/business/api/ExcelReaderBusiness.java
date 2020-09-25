package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.InputExcelDto;

public interface ExcelReaderBusiness {
    void readAndSave(InputExcelDto dto);

}
