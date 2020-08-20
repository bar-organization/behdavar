package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = AssuranceEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class AssuranceEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "ASSURANCE";
    public static final String SEQ_NAME = "ASSURANCE" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AssuranceEntity.SEQ_NAME)
    @SequenceGenerator(name = AssuranceEntity.SEQ_NAME, sequenceName = AssuranceEntity.SEQ_NAME)
    private Long id;

}