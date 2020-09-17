package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.PersonConstant;
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
@Table(name = PersonConstant.TABLE_NAME, schema = PersonConstant.SCHEMA,
        uniqueConstraints = @UniqueConstraint(columnNames = {PersonConstant.FIRST_NAME, PersonConstant.LAST_NAME}))
public class PersonEntity extends BaseAuditorEntity<String, Long> {

    public static final String SEQ_NAME = "PERSON" + BaseConstant.SEQUENCE;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PersonEntity.SEQ_NAME)
    @SequenceGenerator(name = PersonEntity.SEQ_NAME, sequenceName = PersonEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = PersonConstant.FIRST_NAME)
    private String firstName;

    @Column(name = PersonConstant.LAST_NAME)
    private String lastName;

    @Column(name = PersonConstant.FULL_NAME)
    private String fullName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "NATIONAL_CODE")
    private String nationalCode;

    @Column(name = PersonConstant.EMAIL)
    private String email;

    @Column(name = PersonConstant.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = PersonConstant.DESCRIPTION)
    private String description;


}
