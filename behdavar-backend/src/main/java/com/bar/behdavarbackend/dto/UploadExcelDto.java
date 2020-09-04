package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UploadExcelDto extends BaseAuditorDto<String, Long> {

    @NotNull(message = "validation.error.not.null")
    private String fileName;
    @NotNull(message = "validation.error.not.null")
    private String content;
}
