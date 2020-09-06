package com.bar.behdavardatabase.entity;

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

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = CartableEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class CartableEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "CARTABLE";
    public static final String SEQ_NAME = "MESSAGE" + BaseConstant.SEQUENCE;
    public static final String SENDER = "sender";
    public static final String RECEIVER = "receiver";
    public static final String CONTRACT = "contract";
    public static final String ACTIVE = "active";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CartableEntity.SEQ_NAME)
    @SequenceGenerator(name = CartableEntity.SEQ_NAME, sequenceName = CartableEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "ACTIVE")
    @NotNull
    private Boolean active;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_ID", nullable = false, foreignKey = @ForeignKey(name = "CARTABLE_SENDER_FK"))
    private UserEntity sender;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID", nullable = false, foreignKey = @ForeignKey(name = "CARTABLE_RECEIVER_FK"))
    private UserEntity receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false, foreignKey = @ForeignKey(name = "CARTABLE_CONTRACT_FK"))
    private ContractEntity contract;

}