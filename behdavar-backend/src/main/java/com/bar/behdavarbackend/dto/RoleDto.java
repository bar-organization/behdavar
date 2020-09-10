package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RoleDto extends BaseAuditorDto<String, Long> {
    @NotNull(message = "validation.error.not.null")
    private String roleName;
    @NotNull(message = "validation.error.not.null")
    private String title;
    private List<String> privileges;
}
