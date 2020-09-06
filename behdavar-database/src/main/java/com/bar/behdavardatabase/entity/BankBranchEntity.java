package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;
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
@Table(name = BankBranchEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class BankBranchEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "BANK";
    public static final String SEQ_NAME = "BANK" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BankBranchEntity.SEQ_NAME)
    @SequenceGenerator(name = BankBranchEntity.SEQ_NAME, sequenceName = BankBranchEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "CODE", nullable = false, length = 5)
    private String code;

    @Column(name = "NAME")
    private String name;


    @Column(name = "ACTIVE")
    private Boolean active;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANK_TYPE_ID", foreignKey = @ForeignKey(name = "BANK_BANK_TYPE_FK")
            , nullable = false)
    private CatalogDetailEntity bankType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "BANK_ADDRESS_FK"))
    private AddressEntity address;

}