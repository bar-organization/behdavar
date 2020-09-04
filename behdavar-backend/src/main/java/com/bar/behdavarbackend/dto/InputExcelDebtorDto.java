package com.bar.behdavarbackend.dto;

import com.poiji.annotation.ExcelSheet;
import lombok.Data;


@Data
@ExcelSheet("مدیون")
public class InputExcelDebtorDto extends InputExcelPersonDto {
}
