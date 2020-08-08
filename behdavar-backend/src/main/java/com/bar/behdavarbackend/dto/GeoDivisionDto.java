package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.GeoDivisionType;
import lombok.Data;

import java.util.Set;

@Data
public class GeoDivisionDto extends BaseAuditorDto<Long, Long> {

    private String code;
    private String name;
    private GeoDivisionType geoDivisionType;
    private GeoDivisionDto parent;
    private Set<GeoDivisionDto> children;

}