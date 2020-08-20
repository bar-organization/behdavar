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
@Table(name = PursuitLogEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class PursuitLogEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PURSUIT_LOG";
    public static final String SEQ_NAME = "PURSUIT_LOG" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PursuitLogEntity.SEQ_NAME)
    @SequenceGenerator(name = PursuitLogEntity.SEQ_NAME, sequenceName = PursuitLogEntity.SEQ_NAME)
    private Long id;

}