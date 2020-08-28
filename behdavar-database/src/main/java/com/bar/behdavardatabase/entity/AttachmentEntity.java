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
@Table(name = AttachmentEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class AttachmentEntity extends BaseAuditorEntity<String, Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "ATTACHMENT";
    public static final String SEQ_NAME = "ATTACHMENT" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AttachmentEntity.SEQ_NAME)
    @SequenceGenerator(name = AttachmentEntity.SEQ_NAME, sequenceName = AttachmentEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @Lob
    @Column(name = "CONTENT")
    private byte[] content;

    @Column(name = "FILE_NAME", nullable = false)
    @NotNull
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTACHMENT_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "ATTACHEMNT_TYPE_FK"))
    private CatalogDetailEntity attachmentType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRACT_ID", foreignKey = @ForeignKey(name = "ATTACHMENT_CONTRACT_FK"))
    private ContractEntity contract;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURSUIT_ID", foreignKey = @ForeignKey(name = "ATTACHMENT_PURSUIT_FK"))
    private PursuitEntity pursuit;

}