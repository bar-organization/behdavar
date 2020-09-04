package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = InputExcelDebtorEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class InputExcelDebtorEntity extends InputExcelPersonEntity {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "INPUT_EXCEL_DEBTOR";
    public static final String SEQ_NAME = "INPUT_EXCEL_DEBTOR" + BaseConstant.SEQUENCE;

}