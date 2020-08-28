package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = LendingEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class LendingEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "LENDING";
    public static final String SEQ_NAME = "LENDING" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = LendingEntity.SEQ_NAME)
    @SequenceGenerator(name = LendingEntity.SEQ_NAME, sequenceName = LendingEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;


    @Column(name = "MASTER_AMOUNT", columnDefinition = "NUMBER(24,4)")
    private BigDecimal masterAmount;

    @Column(name = "IDEA_ISSUE_DATE")
    private LocalDate ideaIssueDate;

    @Column(name = "receive_Lending_Date")
    private LocalDate receiveLendingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID", foreignKey = @ForeignKey(name = "LENDIING_BANK_FK"))
    private BankBranchEntity branchBank;

}