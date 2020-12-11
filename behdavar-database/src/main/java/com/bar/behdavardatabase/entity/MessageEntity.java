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

import java.util.HashSet;
import java.util.Set;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = MessageEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class MessageEntity  extends BaseAuditorEntity<String,Long>{

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "MESSAGE";
    public static final String SEQ_NAME = "MESSAGE" + BaseConstant.SEQUENCE;
    public static final String ATTACHMENTS = "attachments";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = MessageEntity.SEQ_NAME)
    @SequenceGenerator(name = MessageEntity.SEQ_NAME, sequenceName = MessageEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Column(name = "MESSAGE_NUMBER", columnDefinition = "VARCHAR(30)")
    private String messageNumber;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(3000)", length = 3000)
    private String description;

    @Column(name = "SUBJECT", columnDefinition = "VARCHAR(300)")
    private String subject;

    @Column(name = "IS_SENT")
    private Boolean isSent;

    @Column(name = "DELETED" , columnDefinition = " NUMBER(1) DEFAULT 0")
    private Boolean deleted;

    @OneToOne
    @JoinColumn(name = "SENDER_ID", foreignKey = @ForeignKey(name = "MESSAGE_SENDER_FK") , nullable = false)
    @NotNull
    private UserEntity sender;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<MessageReceiverEntity> receivers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "message", cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<MessageAttachmentEntity> attachments = new HashSet<>();

}
