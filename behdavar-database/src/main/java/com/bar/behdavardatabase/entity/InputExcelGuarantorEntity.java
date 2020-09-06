package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = InputExcelPersonEntity.class)})
@Entity
@Table(name = InputExcelGuarantorEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class InputExcelGuarantorEntity extends InputExcelPersonEntity {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "INPUT_EXCEL_GUARANTOR";
    public static final String SEQ_NAME = "INPUT_EXCEL_GUARANTOR" + BaseConstant.SEQUENCE;

}