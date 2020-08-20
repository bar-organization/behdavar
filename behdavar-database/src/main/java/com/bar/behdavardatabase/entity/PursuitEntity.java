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
@Table(name = PursuitEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class PursuitEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PURSUIT";
    public static final String SEQ_NAME = "PURSUIT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PursuitEntity.SEQ_NAME)
    @SequenceGenerator(name = PursuitEntity.SEQ_NAME, sequenceName = PursuitEntity.SEQ_NAME)
    private Long id;

}