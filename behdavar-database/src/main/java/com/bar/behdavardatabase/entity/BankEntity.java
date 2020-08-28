package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.CityType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = BankEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class BankEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "BANK";
    public static final String SEQ_NAME = "BANK" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BankEntity.SEQ_NAME)
    @SequenceGenerator(name = BankEntity.SEQ_NAME, sequenceName = BankEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "CODE", nullable = false, length = 5)
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "ADDRESS")
    private String address;


    //TODO : what is this
    @Column(name = "HEADS")
    private String heads;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Bank_TYPE_FK", foreignKey = @ForeignKey(name = "BANK_BANK_TYPE_FK")
            , nullable = false)
    private CatalogDetailEntity bankType;


    @Column(name = "CITY_TYPE")
//    @Convert(converter = CityType.CityTypeTypeConverter.class)
    private CityType cityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEO_DIVISION_FK", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "BANK_GEO_DIVISION_FK"))
    private GeoDivisionEntity city;

}