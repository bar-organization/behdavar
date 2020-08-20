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
@Table(name = CartableLogEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class CartableLogEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CARTABLE_LOG";
    public static final String SEQ_NAME = "CARTABLE_LOG" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CartableLogEntity.SEQ_NAME)
    @SequenceGenerator(name = CartableLogEntity.SEQ_NAME, sequenceName = CartableLogEntity.SEQ_NAME)
    private Long id;

}