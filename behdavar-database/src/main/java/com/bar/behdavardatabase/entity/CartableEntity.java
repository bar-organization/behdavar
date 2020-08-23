package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = CartableEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class CartableEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CARTABLE";
    public static final String SEQ_NAME = "MESSAGE" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CartableEntity.SEQ_NAME)
    @SequenceGenerator(name = CartableEntity.SEQ_NAME, sequenceName = CartableEntity.SEQ_NAME)
    private Long id;

    @NotNull
    @Column(name = "SENDER_ID", nullable = false, columnDefinition = "NUMBER(19)")
    private Long senderId;

    @NotNull
    @Column(name = "RECEIVER_ID", nullable = false, columnDefinition = "NUMBER(19)")
    private Long receiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "CARTABLE_CONTRACT_FK"))
    private ContractEntity contract;

}