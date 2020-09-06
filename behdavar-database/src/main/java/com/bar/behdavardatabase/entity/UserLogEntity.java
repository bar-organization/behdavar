package com.bar.behdavardatabase.entity;

import com.bar.behdavardatabase.common.BaseEntity;
import com.bar.behdavardatabase.constant.ContactConstant;
import com.bar.behdavardatabase.constant.common.BaseConstant;
import com.bar.behdavardatabase.entity.security.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.bar.behdavardatabase.constant.common.BaseConstant.BASE_TABLE_PREFIX;

@Setter
@Getter
@Audited
@Entity
@Table(name = UserLogEntity.TABLE_NAME, schema = ContactConstant.SCHEMA)
public class UserLogEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = BASE_TABLE_PREFIX + "USER_LOG";
    public static final String SEQ_NAME = "USER_LOG" + BaseConstant.SEQUENCE;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = UserLogEntity.SEQ_NAME)
    @SequenceGenerator(name = UserLogEntity.SEQ_NAME, sequenceName = UserLogEntity.SEQ_NAME, allocationSize = ALLOCATION_SIZE)
    private Long id;

    @NotNull
    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDateTime lastLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name = "USER_LOG_USER_FK"))
    private UserEntity user;

}