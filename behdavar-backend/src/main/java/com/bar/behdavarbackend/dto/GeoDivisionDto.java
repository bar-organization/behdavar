package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.GeoDivisionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class GeoDivisionDto extends BaseAuditorDto<String, Long> {

    private String code;
    private String name;
    private GeoDivisionType geoDivisionType;
    private GeoDivisionDto parent;
    private Set<GeoDivisionDto> children;

}