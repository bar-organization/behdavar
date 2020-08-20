package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto extends BaseAuditorDto<String, Long> {
    private String roleName;
    private List<String> privileges;
}
