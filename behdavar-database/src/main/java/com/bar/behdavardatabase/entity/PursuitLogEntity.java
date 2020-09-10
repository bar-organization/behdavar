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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = PursuitLogEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class PursuitLogEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "PURSUIT_LOG";
    public static final String SEQ_NAME = "PURSUIT_LOG" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PursuitLogEntity.SEQ_NAME)
    @SequenceGenerator(name = PursuitLogEntity.SEQ_NAME, sequenceName = PursuitLogEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
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
    @JoinColumn(name = "PAYMENT_ID", foreignKey = @ForeignKey(name = "PURSUIT_LOG_PAYMENT_FK"))
    private PaymentEntity payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "PURSUIT_LOG_USER_FK"), nullable = false)
    @NotNull
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", foreignKey = @ForeignKey(name = "PURSUIT_LOG_CONTRACT_FK"))
    private ContractEntity contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURSUIT_ID", foreignKey = @ForeignKey(name = "PURSUIT_LOG_PURSUIT_FK"))
    private PursuitEntity pursuit;


}