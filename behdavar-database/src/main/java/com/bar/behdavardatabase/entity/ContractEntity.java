package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.bar.behdavarcommon.enumeration.ContractType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@NoArgsConstructor
@Table(name = ContractEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class ContractEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CONTRACT";
    public static final String SEQ_NAME = "CONTRACT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ContractEntity.SEQ_NAME)
    @SequenceGenerator(name = ContractEntity.SEQ_NAME, sequenceName = ContractEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "submit_Date")
    private LocalDate submitDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LENDING_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_LENDING_FK"))
    private LendingEntity lending;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_PRODUCT_FK"))
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CORPORATION_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTRACT_CORPORATION_FK"))
    private CatalogDetailEntity corporation;
    @Column(name = "CONTRACT_STATUS", nullable = false, columnDefinition = "NUMBER(2)")
    private ContractStatus contractStatus;
    @Column(name = "CONTRACT_TYPE", nullable = false, columnDefinition = "Number(2)")
    private ContractType contractType;

    private ContractEntity(Long id) {
        this.id = id;
    }

}