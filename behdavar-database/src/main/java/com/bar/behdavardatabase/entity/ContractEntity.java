package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = ContractEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class ContractEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CONTRACT";
    public static final String SEQ_NAME = "CONTRACT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ContractEntity.SEQ_NAME)
    @SequenceGenerator(name = ContractEntity.SEQ_NAME, sequenceName = ContractEntity.SEQ_NAME)
    private Long id;

    public ContractEntity(Long id) {
        this.id = id;
    }

    @Column(name = "MASTER_AMOUNT", nullable = false, columnDefinition = "NUMBER(24,4)")
    public BigDecimal masterAmount;

    @Column(name = "deffered_Amount", columnDefinition = "NUMBER(24,4)")
    public BigDecimal defferedAmount;

    @Column(name = "IDEAL_ISSUE_DATE")
    public LocalDate idealIssueDate;

    @Column(name = "LENDING_NUMBER", columnDefinition = "VARCHAR(30)")
    public String lendingNumber;

    @Column(name = "DEFFERED_COUNT")
    public Long defferedCount;

    @Column(name = "late_Fees", columnDefinition = "NUMBER(24,4)")
    public BigDecimal lateFees;

    @Column(name = "submit_Date")
    public LocalDate submitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LENDING_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_LENDING_FK"))
    public LendingEntity lending;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_PRODUCT_FK"))
    public ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CORPORATION_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_CORPORATION_FK"))
    public CatalogDetailEntity corporation;

    @Column(name = "CONTRACT_STATUS", nullable = false, columnDefinition = "NUMBER(2)")
    public ContractStatus contractStatus;

    @Column(name = "CONTRACT_TYPE", nullable = false, columnDefinition = "Number(2)")
    public ContractType contractType;

}