package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode()
public class RoleDto extends BaseAuditorDto<String, Long> {
    @NotNull(message = "validation.error.not.null")
    private String roleName;
    @NotNull(message = "validation.error.not.null")
    private String title;

    //TODO : check this with reza for use PrivilegesDto instead of this
    private List<String> privileges;

    private List<PrivilegeDto> privilegeDtos = new ArrayList<>();


}
