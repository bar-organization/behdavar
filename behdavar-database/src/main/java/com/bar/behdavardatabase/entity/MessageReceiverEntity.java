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
@Table(name = MessageReceiverEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class MessageReceiverEntity  extends BaseAuditorEntity<String,Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "MESSAGE_RECEIVER";
    public static final String SEQ_NAME = "MESSAGE_RECEIVER" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = MessageReceiverEntity.SEQ_NAME)
    @SequenceGenerator(name = MessageReceiverEntity.SEQ_NAME, sequenceName = MessageReceiverEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "IS_CC")
    private Boolean isCC;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID", foreignKey = @ForeignKey(name = "MESSAGE_RECEIVER_FK"))
    private UserEntity receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID", foreignKey = @ForeignKey(name = "MESSAGE_MESSAGE_FK"))
    private MessageEntity message;

}
