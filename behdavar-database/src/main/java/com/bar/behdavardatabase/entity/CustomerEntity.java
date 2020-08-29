package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = CustomerEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class CustomerEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CUSTOMER";
    public static final String SEQ_NAME = "CUSTOMER" + BaseConstant.SEQUENCE;
    public static final String PERSON = "person";
    public static final String CONTRACT = "contract";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CustomerEntity.SEQ_NAME)
    @SequenceGenerator(name = CustomerEntity.SEQ_NAME, sequenceName = CustomerEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false, foreignKey = @ForeignKey(name = "CUSTOMER_PERSON_FK"))
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "CUSTOMER_CONTRACT_FK"))
    private ContractEntity contract;

    //TODO : add catalog detail

}