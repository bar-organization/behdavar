package com.bar.behdavarbackend.business.api;

import com.bar.behdavarbackend.dto.UploadExcelDto;

public interface ExcelReaderBusiness {
    void readAndSave(UploadExcelDto dto);
}
