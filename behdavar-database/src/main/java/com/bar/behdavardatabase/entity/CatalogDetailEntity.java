package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.CatalogDetailConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.CatalogDetailConstant.SEQ_NAME;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = CatalogDetailConstant.TABLE_NAME, schema = CatalogDetailConstant.SCHEMA)
public class CatalogDetailEntity extends BaseAuditorEntity<String, Long> {

    @Column(name = CatalogDetailConstant.ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @Column(name = CatalogDetailConstant.ENGLISH_TITLE)
    private String englishTitle;

    @Column(name = CatalogDetailConstant.TITLE, nullable = false)
    private String title;

    @Column(name = CatalogDetailConstant.CODE, length = 5, nullable = false)
    private String code;

    @Column(name = CatalogDetailConstant.ACTIVE)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CatalogDetailConstant.CATALOG,
            foreignKey = @ForeignKey(name = CatalogDetailConstant.CATALOG_FK_CONSTRAINT),
            nullable = false)
    private CatalogEntity catalog;

}
