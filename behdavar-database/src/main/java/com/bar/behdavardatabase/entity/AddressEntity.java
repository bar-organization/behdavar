package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.AddressConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Setter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = AddressConstant.TABLE_NAME, schema = AddressConstant.SCHEMA)
public class AddressEntity extends BaseAuditorEntity<String, Long> {

    @Column(name = "ID")
    @Id
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

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR( 60)")
    private String description;

    @JoinColumn(name = "GEO_DIVISION_ID", nullable = false, foreignKey = @ForeignKey(name = "ADDRESS_GEO_DIVISION_FK"))
    @OneToOne
    private GeoDivisionEntity geoDivision;

    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "ADDRESS_PERSON_FK"))
    @ManyToOne
    private PersonEntity person;

}
