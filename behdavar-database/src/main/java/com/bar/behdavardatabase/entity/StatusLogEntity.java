package com.bar.behdavardatabase.entity;

import com.bar.behdavarcommon.enumeration.ContractStatus;
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

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = StatusLogEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class StatusLogEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "STATUS_LOG";
    public static final String SEQ_NAME = "STATUS_LOG" + BaseConstant.SEQUENCE;
    public static final String STATUS = "status";
    public static final String USER = "user";
    public static final String CONTRACT = "contract";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = StatusLogEntity.SEQ_NAME)
    @SequenceGenerator(name = StatusLogEntity.SEQ_NAME, sequenceName = StatusLogEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "CONTRACT_STATUS", nullable = false, columnDefinition = "NUMBER(2)")
    private ContractStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name = "STATUS_LOG_USER_FK"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "STATUS_LOG_CONTRACT_FK"))
    private ContractEntity contract;

}