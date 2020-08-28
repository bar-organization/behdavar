package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.GeoDivisionType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = GeoDivisionEntity.TABLE_NAME, schema = ContactConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class GeoDivisionEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "GEO_DIVISION";
    public static final String SEQ_NAME = "CONTACT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GeoDivisionEntity.SEQ_NAME)
    @SequenceGenerator(name = GeoDivisionEntity.SEQ_NAME, sequenceName = GeoDivisionEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "CODE", nullable = false, length = 5)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;


    @Column(name = "TYPE", nullable = false)
    private GeoDivisionType geoDivisionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEO_DIVISION_FK", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "GEO_DIVISION_SELF_FK"))
    private GeoDivisionEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<GeoDivisionEntity> children;


}