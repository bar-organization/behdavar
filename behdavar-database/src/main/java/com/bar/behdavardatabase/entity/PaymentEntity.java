package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.PaymentType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import com.bar.behdavardatabase.entity.security.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Entity
@Table(name = PaymentEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class PaymentEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PAYMENT";
    public static final String SEQ_NAME = "PAYMENT" + BaseConstant.SEQUENCE;
    public static final String AMOUNT = "amount";
    public static final String PAYMENT_DATE = "paymentDate";
    public static final String PAYMENT_TYPE = "paymentType";
    public static final String CONTRACT = "contract";
    public static final String USER = "user";
    public static final String ATTACHMENT = "attachment";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PaymentEntity.SEQ_NAME)
    @SequenceGenerator(name = PaymentEntity.SEQ_NAME, sequenceName = PaymentEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "AMOUNT", nullable = false, columnDefinition = "NUMBER(24,4)")
    private BigDecimal amount;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "PAYMENT_TYPE", nullable = false)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "PAYMENT_CONTRACT_FK"))
    private ContractEntity contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name = "PAYMENT_USER_FK"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTACHMENT_ID", foreignKey = @ForeignKey(name = "PAYMENT_ATTACHMENT_FK"))
    private AttachmentEntity attachment;

}