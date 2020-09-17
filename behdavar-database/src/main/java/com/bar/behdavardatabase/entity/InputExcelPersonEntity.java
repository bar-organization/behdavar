package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InputExcelPersonEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "INPUT_EXCEL_PERSON";
    public static final String SEQ_NAME = "INPUT_EXCEL_PERSON" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = InputExcelPersonEntity.SEQ_NAME)
    @SequenceGenerator(name = InputExcelPersonEntity.SEQ_NAME, sequenceName = InputExcelPersonEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "ROW_INDEX")
    private Integer rowIndex;
    @Column(name = "ROW_NO")
    private Integer rowNo;
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "NATIONAL_CODE")
    private String nationalCode;
    @Column(name = "IDENTIFICATION_CODE")
    private String identificationCode;
    @Column(name = "BIRTH_PLACE")
    private String birthPlace;
    @Column(name = "BIRTH_DATE")
    private String birthDate;
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE1")
    private String mobile1;
    @Column(name = "MOBILE2")
    private String mobile2;
    @Column(name = "TEL1")
    private String tel1;
    @Column(name = "TEL2")
    private String tel2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INPUT_EXCEL_ID", nullable = false)
    private InputExcelEntity inputExcel;

}