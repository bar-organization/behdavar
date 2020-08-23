package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.PhoneType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.ContactConstant.SEQ_NAME;

@Setter
@Getter
@Entity
@Table(name = ContactConstant.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class ContactEntity extends BaseAuditorEntity<String, Long> {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String number;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(100)")
    private String description;

    @Column(name = "PRE_CODE", columnDefinition = "VARCHAR(4)")
    private String preCode;

    @Column(name = "CONFIRMED")
    private Boolean confirmed;

    @Column(name = "PHONE_TYPE", nullable = false)
    private PhoneType phoneType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTACT_PERSON_FK"))
    private PersonEntity person;

}
