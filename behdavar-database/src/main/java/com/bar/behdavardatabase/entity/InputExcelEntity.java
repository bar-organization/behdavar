package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = InputExcelEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class InputExcelEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "INPUT_EXCEL";
    public static final String SEQ_NAME = "INPUT_EXCEL" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = InputExcelEntity.SEQ_NAME)
    @SequenceGenerator(name = InputExcelEntity.SEQ_NAME, sequenceName = InputExcelEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "FILE_NAME", nullable = false)
    @NotNull
    private String fileName;

    @Column(name = "CONTENT", nullable = false)
    @Lob
    @NotNull
    private byte[] content;

}