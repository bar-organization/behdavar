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

import java.math.BigDecimal;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = InputExcelLendingEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class InputExcelLendingEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "INPUT_EXCEL_LENDING";
    public static final String SEQ_NAME = "INPUT_EXCEL_LENDING" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = InputExcelLendingEntity.SEQ_NAME)
    @SequenceGenerator(name = InputExcelLendingEntity.SEQ_NAME, sequenceName = InputExcelLendingEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "ROW_INDEX")
    private int rowIndex;
    @Column(name = "ROW_NO")
    private Integer rowNo;
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "LEGAL")
    private Integer legal;
    @Column(name = "BRANCH")
    private String branch;
    @Column(name = "BRANCH_CODE")
    private Integer branchCode;
    @Column(name = "CONTRACT_TYPE")
    private String contractType;
    @Column(name = "CONTRACT_TYPE_CODE")
    private Long contractTypeCode;
    @Column(name = "RECEIVE_DATE")
    private String receiveDate;
    @Column(name = "DUE_DATE")
    private String dueDate;
    @Column(name = "INPUT_DATE")
    //todo:important
    private String inputDate;
    @Column(name = "AMOUNT")
    private Long amount;
    @Column(name = "INSTALLMENT_AMOUNT")
    private BigDecimal installmentAmount;
    @Column(name = "INSTALLMENT_COUNT")
    private Long installmentCount;
    @Column(name = "REMAIN_DEBT_AMOUNT")
    private BigDecimal remainDebtAmount;
    @Column(name = "DEBT_AMOUNT")
    private BigDecimal debtAmount;
    @Column(name = "LATE_FEES")
    private BigDecimal lateFees;
    @Column(name = "DIFFERED_INSTALLMENT_COUNT")
    private Long differedInstallmentCount;
    @Column(name = "LAST_PAY_INSTALLMENT_DATE")
    private String lastPayInstallmentDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EXPERT")
    private String expert;
    @Column(name = "EXPERT_CODE")
    private Long expertCode;
    @Column(name = "MORTGAGE_AMOUNT")
    private Long mortgageAmount;
    @Column(name = "MORTGAGE_DATE")
    private String mortgageDate;
    @Column(name = "PLEDGE_TYPE")
    private Long pledgeType;
    @Column(name = "PLEDGE_CODE")
    private Long pledgeCode;
    @Column(name = "BAIL_NAME")
    private String bailName;
    @Column(name = "BAIL_TEL")
    private String bailTel;
    @Column(name = "BAIL_ADDRESS")
    private String bailAddress;
    @Column(name = "MACHINE_TYPE")
    private Long machineType;
    @Column(name = "MACHINE")
    private String machine;
    @Column(name = "SHASI_NUMBER")
    private String shasiNumber;
    @Column(name = "MOTOR_NUMBER")
    private String motorNumber;
    @Column(name = "PLAQUE_NUMBER")
    private String plaqueNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INPUT_EXCEL_ID", nullable = false, foreignKey = @ForeignKey(name = "INPUT_EXCEL_LENDING_FILE_FK"))
    private InputExcelEntity inputExcel;

}