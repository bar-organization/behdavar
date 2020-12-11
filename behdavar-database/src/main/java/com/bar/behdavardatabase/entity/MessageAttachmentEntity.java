package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseAuditorEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@AuditOverrides({@AuditOverride(forClass = BaseAuditorEntity.class)})
@Entity
@Table(name = MessageAttachmentEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class MessageAttachmentEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "MESSAGE_ATTACHMENT";
    public static final String SEQ_NAME = "MESSAGE_ATTACHMENT" + BaseConstant.SEQUENCE;
    public static final String CONTENT = "content";
    public static final String FILENAME = "fileName";
    public static final String MESSAGE = "message";

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = MessageAttachmentEntity.SEQ_NAME)
    @SequenceGenerator(name = MessageAttachmentEntity.SEQ_NAME, sequenceName = MessageAttachmentEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Lob
    @Column(name = "CONTENT")
    private byte[] content;

    @Column(name = "FILE_NAME", nullable = false)
    @NotNull
    private String fileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_ID", foreignKey = @ForeignKey(name = "ATTACHMENT_MESSAGE_FK"))
    private MessageEntity message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MessageAttachmentEntity that = (MessageAttachmentEntity) o;
        return id.equals(that.id) &&
                fileName.equals(that.fileName) &&
                message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, fileName, message);
    }
}