package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.RelationType;
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
@Table(name = GuarantorEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class GuarantorEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "GUARANTOR";
    public static final String SEQ_NAME = "GUARANTOR" + BaseConstant.SEQUENCE;
    public static final String RELATION_TYPE = "relationType";
    public static final String CONTRACT = "contract";
    public static final String PERSON = "person";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GuarantorEntity.SEQ_NAME)
    @SequenceGenerator(name = GuarantorEntity.SEQ_NAME, sequenceName = GuarantorEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "RELATION_TYPE")
    private RelationType relationType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "GUARANTOR_CONTRACT_FK"))
    private ContractEntity contract;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false, foreignKey = @ForeignKey(name = "GUARANTOR_PERSON_FK"))
    private PersonEntity person;

}