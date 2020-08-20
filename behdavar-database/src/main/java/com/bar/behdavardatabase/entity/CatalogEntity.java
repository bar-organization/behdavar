package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.CatalogConstant;
import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.CatalogConstant.SEQ_NAME;

@Setter
@Getter
@Entity
@Table(name = CatalogConstant.TABLE_NAME, schema = CatalogConstant.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = BaseCodeTitleConstant.CODE))
public class CatalogEntity extends BaseAuditorEntity<String, Long>  {

    @Column(name = CatalogConstant.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
    private Long id;

    @Column(name = CatalogConstant.CODE, length = 5)
    private String code;

    @Column(name = CatalogConstant.ENGLISH_TITLE)
    private String englishTitle;

    @Column(name = CatalogConstant.TITLE)
    private String title;

    @Column(name = CatalogConstant.ACTIVE)
    private Boolean active;

}
