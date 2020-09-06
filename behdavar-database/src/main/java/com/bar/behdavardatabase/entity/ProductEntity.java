package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
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
@Table(name = ProductEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class ProductEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PRODUCT";
    public static final String SEQ_NAME = "PRODUCT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ProductEntity.SEQ_NAME)
    @SequenceGenerator(name = ProductEntity.SEQ_NAME, sequenceName = ProductEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "PRODUCT_SHASI_NUMBER", columnDefinition = "VARCHAR(50)")
    private String productShasiNumber;

    @Column(name = "PRODUCT_PLATE", columnDefinition = "VARCHAR(50)")
    private String productPlate;

    @Column(name = "PRODUCT_NAME", columnDefinition = "VARCHAR(100)")
    private String productName;
}