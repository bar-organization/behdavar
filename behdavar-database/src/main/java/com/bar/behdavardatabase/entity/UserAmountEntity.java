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
import java.math.BigDecimal;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = UserAmountEntity.TABLE_NAME, schema = ContactConstant.SCHEMA,
        uniqueConstraints = @UniqueConstraint(name = "USER_AMOUNT_USER_UK", columnNames = "USER_ID"))
public class UserAmountEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "USER_AMOUNT";
    public static final String SEQ_NAME = "USER_AMOUNT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = UserAmountEntity.SEQ_NAME)
    @SequenceGenerator(name = UserAmountEntity.SEQ_NAME, sequenceName = UserAmountEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "TOTAL_AMOUNT", columnDefinition = "NUMBER(24,4)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "RECEIVE_AMOUNT", columnDefinition = "NUMBER(24,4)", nullable = false)
    private BigDecimal receiveAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name = "USER_AMOUNT_USER_ID"))
    private UserEntity user;

}