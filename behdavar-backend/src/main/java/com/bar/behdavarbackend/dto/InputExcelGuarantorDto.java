package com.bar.behdavarbackend.dto;

import com.poiji.annotation.ExcelSheet;
import lombok.Data;

@ExcelSheet("ضامن")
@Data
public class InputExcelGuarantorDto extends InputExcelPersonDto {
}
