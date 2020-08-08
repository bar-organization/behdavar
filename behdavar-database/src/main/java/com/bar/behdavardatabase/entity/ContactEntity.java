package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.PhoneType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import lombok.Data;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.ContactConstant.SEQ_NAME;

@Data
@Entity
@Table(name = ContactConstant.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class ContactEntity extends BaseAuditorEntity<String, Long> {

    @Column(name = ContactConstant.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String number;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CONFIRMED")
    private Boolean confirmed;

    @Column(name = "PHONE_TYPE", nullable = false)
    private PhoneType phoneType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "CONTACT_PERSON_FK"))
    private PersonEntity person;

}
