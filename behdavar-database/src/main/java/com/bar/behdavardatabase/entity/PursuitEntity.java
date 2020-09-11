package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.PursuitType;
import com.bar.behdavarcommon.enumeration.ResultType;
import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import com.bar.behdavardatabase.entity.security.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = PursuitEntity.TABLE_NAME, schema = ContactConstant.SCHEMA,uniqueConstraints = @UniqueConstraint(name = "CONTRACT_PURSUIT_UK", columnNames = "CONTRACT_ID"))
public class PursuitEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PURSUIT";
    public static final String SEQ_NAME = "PURSUIT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PursuitEntity.SEQ_NAME)
    @SequenceGenerator(name = PursuitEntity.SEQ_NAME, sequenceName = PursuitEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(200)")
    private String description;

    @Column(name = "COORDINATE_APPOINTMENT")
    private Boolean coordinateAppointment;

    @Column(name = "DEPOSIT_APPOINTMENT")
    private Boolean depositAppointment;

    @Column(name = "SUBMIT_ACCORDING_FINAL")
    private Boolean submitAccordingFinal;

    @Column(name = "NEXT_PURSUIT_DATE")
    private LocalDate nextPursuitDate;

    @Column(name = "CUSTOMER_DEPOSIT")
    private Boolean customerDeposit;

    @Column(name = "PURSUIT_TYPE", columnDefinition = "NUMBER(4)")
    private PursuitType pursuitType;

    @Column(name = "RESULT_TYPE", columnDefinition = "NUMBER(4)")
    private ResultType resultType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_ID", foreignKey = @ForeignKey(name = "PURSUIT_PAYMENT_FK"))
    private PaymentEntity payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "PURSUIT_USER_FK"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", foreignKey = @ForeignKey(name = "PURSUIT_CONTRACT_FK"))
    private ContractEntity contract;

}