package com.bar.behdavarbackend.business.transformer;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavardatabase.common.BaseAuditorEntity;

public class BaseAuditorTransformer {
    public static <E extends BaseAuditorEntity<String, Long>, D extends BaseAuditorDto<String, Long>> void transformAuditingFields(E e, D d) {
        d.setCreatedBy(e.getCreatedBy());
        d.setCreatedDate(e.getCreatedDate());
        d.setLastModifiedBy(e.getLastModifiedBy());
        d.setLastModifiedDate(e.getLastModifiedDate());
        d.setVersion(e.getVersion());
    }
}
