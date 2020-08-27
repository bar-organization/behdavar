package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.RelationType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = GuarantorEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class GuarantorEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "GUARANTOR";
    public static final String SEQ_NAME = "GUARANTOR" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GuarantorEntity.SEQ_NAME)
    @SequenceGenerator(name = GuarantorEntity.SEQ_NAME, sequenceName = GuarantorEntity.SEQ_NAME)
    private Long id;

    @NotNull
    @Column(name = "RELATION_TYPE", nullable = false)
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