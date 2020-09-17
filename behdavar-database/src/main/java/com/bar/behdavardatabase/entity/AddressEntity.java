package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.AddressConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Getter
@Setter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = AddressConstant.TABLE_NAME, schema = AddressConstant.SCHEMA)
public class AddressEntity extends BaseAuditorEntity<String, Long> {

    public static final String SEQ_NAME = "ADDRESS" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AddressEntity.SEQ_NAME)
    @SequenceGenerator(name = AddressEntity.SEQ_NAME, sequenceName = AddressEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "MAIN_STREET", columnDefinition = "VARCHAR( 30)")
    private String mainStreet;

    @Column(name = "SUB_STREET", columnDefinition = "VARCHAR( 30)")
    private String subStreet;

    @Column(name = "MAIN_ALLEY", columnDefinition = "VARCHAR( 30)")
    private String mainAlley;

    @Column(name = "SUB_ALLEY", columnDefinition = "VARCHAR( 30)")
    private String subAlley;

    @Column(name = "POSTAL_CODE", columnDefinition = "VARCHAR( 15)")
    private String postalCode;

    @Column(name = "Plate", columnDefinition = "VARCHAR(10)")
    private String plate;

    @Column(name = "DESCRIPTION",nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;

    @JoinColumn(name = "GEO_DIVISION_ID", foreignKey = @ForeignKey(name = "ADDRESS_GEO_DIVISION_FK"))
    @OneToOne
    private GeoDivisionEntity geoDivision;

    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "ADDRESS_PERSON_FK"))
    @ManyToOne
    private PersonEntity person;

}
